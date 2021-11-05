package com.better.eat.service.impl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.constant.GeneralResponseMessageConstant;
import com.better.eat.domain.document.UserDocument;
import com.better.eat.service.UserService;
import com.better.eat.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseBean addUser(UserDocument bean) {
        Objects.requireNonNull(bean, "bean is null");
        ResponseBean rb = new ResponseBean();
        if (bean.getId() != null) {
            //update user info
            updateUser(bean);
        }
        if (0 == bean.getPhoneNumber()) { rb.setCode(200);
            rb.setDesc("phone number should not be null");
            return rb;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("phoneNumber").is(bean.getPhoneNumber()))
                .addCriteria(Criteria.where("isDel").is(false));
        List<UserDocument> userList = mongoTemplate.find(query, UserDocument.class);
        if (CollectionUtils.isNotEmpty(userList)) {
            rb.setCode(500);
            rb.setDesc("phone number is used");
            return rb;
        }
        bean.setIsDel(false);
        bean.setCreatedTime(System.currentTimeMillis());
        mongoTemplate.save(bean);
        rb.setCode(200);
        rb.setDesc(GeneralResponseMessageConstant.SUCCESS);
        return rb;
    }

    @Override
    public ResponseBean updateUser(UserDocument bean) {
        Objects.requireNonNull(bean, "bean is null");
        Objects.requireNonNull(bean.getId(), "bean's id  is null");
        ResponseBean rb = new ResponseBean();
        boolean isDel = validIsDel(bean.getId());
        if (isDel){
            rb.setCode(500);
            rb.setDesc("bean is deleted");
            return rb;
        }
        mongoTemplate.save(bean);
        rb.setCode(200);
        rb.setDesc(GeneralResponseMessageConstant.SUCCESS);
        return rb;
    }

    @Override
    public ResponseBean deleteUser(String id) {
        Objects.requireNonNull(id, "id is null");
        ResponseBean rb = new ResponseBean();
        boolean isDel = validIsDel(id);
        if (isDel){
            rb.setCode(500);
            rb.setDesc("bean is deleted");
            return rb;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id))
                .addCriteria(Criteria.where("isDel").is(false));
        Update update = new Update();
        update.set("isDel",true);
        mongoTemplate.updateMulti(query,update,UserDocument.class);
        rb.setCode(200);
        rb.setDesc(GeneralResponseMessageConstant.SUCCESS);
        return rb;
    }

    @Override
    public ResponseBean getUserByConditions(HashMap<String, String> params) {
        Objects.requireNonNull(params, "params is null");
        ResponseBean rb = new ResponseBean();
        if (0 == params.size()){
            List<UserDocument> users = mongoTemplate.findAll(UserDocument.class);
            if (CollectionUtils.isNotEmpty(users)){
                List<UserDocument> collect = users.stream().filter(item -> item.getIsDel().equals(false)).collect(Collectors.toList());
                rb.setData(collect);
            }else{
                rb.setData(users);
            }
            rb.setCode(200);
            rb.setDesc(GeneralResponseMessageConstant.SUCCESS);
            return rb;
        }
        Set<String> keys = params.keySet();
        Query query = new Query();
        for (String key:keys){
            Object o = params.get(key);
            if(o instanceof Collection){
                List arr = (List)o;
                if(arr.size()>0) {
                    query.addCriteria(Criteria.where(key).in(arr));
                }
                continue;
            }
            if(null != o && StringUtils.isNotEmpty(String.valueOf(o))) {
                if (key.equals("name")) {
                    String qualifiedString = StringUtil.qualifiedString(String.valueOf(o));
                    String regex = ".*?" + qualifiedString + ".*";
                    query.addCriteria(new Criteria().orOperator(Criteria.where("name").regex(regex,"i"), Criteria.where("privateMaterialNumber").regex(regex,"i")));
                    continue;
                }
                query.addCriteria(Criteria.where(key).is(o));
            }
        }
        query.addCriteria(Criteria.where("isDel").is(false));
        List<UserDocument> userDocuments = mongoTemplate.find(query, UserDocument.class);
        rb.setCode(200);
        rb.setData(userDocuments);
        rb.setDesc(GeneralResponseMessageConstant.SUCCESS);
        return rb;
    }

    /**
     * @param id user id
     * @return true -deleted  | false -none
     *
     */
    private boolean validIsDel(String id) {
        UserDocument byId = mongoTemplate.findById(id, UserDocument.class);
        if (null == byId||byId.getIsDel()){
           return true;
        }
        return false;
    }
}
