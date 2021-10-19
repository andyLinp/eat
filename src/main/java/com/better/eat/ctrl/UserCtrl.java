package com.better.eat.ctrl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.document.UserDocument;
import com.better.eat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andy
 */
@RestController
@RequestMapping(value = "/user")
public class UserCtrl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    @Autowired
    public UserCtrl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity addUser(@RequestBody UserDocument bean){
        ResponseBean responseBean = new ResponseBean();
        try{
            ResponseBean rb = userService.addUser(bean);
            responseBean.setResult(bean);
            responseBean.setExtInfo("新增成功");
            responseBean.setSuccess(true);
            return ResponseEntity.ok(rb);
        }catch (RuntimeException e) {
            responseBean.setErrorMsg(e.getMessage());
            responseBean.setSuccess(false);
            return ResponseEntity.ok(responseBean);
        } catch (Exception e){
            logger.error(e.toString());
            responseBean.setErrorMsg(e.toString());
            responseBean.setSuccess(false);
            return  new ResponseEntity(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
