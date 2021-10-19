package com.better.eat.service.impl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.constant.GeneralResponseMessageConstant;
import com.better.eat.domain.document.UserDocument;
import com.better.eat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Query;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseBean addUser(UserDocument bean) {
        Objects.requireNonNull(bean, "bean should not be null");
        ResponseBean rb = new ResponseBean();
        if (bean.getId() != null) {
            //update user info
        }
        if (0 == bean.getPhoneNumber()) { rb.setSuccess(false);
            rb.setErrorMsg("phone number should not be null");
            return rb;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("phoneNumber").is(bean.getPhoneNumber()))
                .addCriteria(Criteria.where("isDel").is(false));
        List<UserDocument> userList = mongoTemplate.find(query, UserDocument.class);
        if (CollectionUtils.isNotEmpty(userList)) {
            rb.setSuccess(false);
            rb.setErrorMsg("手机号已存在");
            return rb;
        }
        mongoTemplate.save(bean);
        rb.setSuccess(true);
        rb.setExtInfo(GeneralResponseMessageConstant.SUCCESS);
        return rb;
    }
}
