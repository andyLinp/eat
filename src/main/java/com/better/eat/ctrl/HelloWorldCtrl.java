package com.better.eat.ctrl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.service.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/helloWorld")
public class HelloWorldCtrl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HelloWorldService service;
    @Autowired
    public HelloWorldCtrl(HelloWorldService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseBean helloWorld(String world){
        ResponseBean responseBean = new ResponseBean();
        try{
            String line = service.getWorld(world);
            responseBean.setSuccess(true);
            responseBean.setResult(line);
            responseBean.setExtInfo("Hello World!");
            return responseBean;
        }catch (Exception e){
            responseBean.setSuccess(false);
            responseBean.setErrorMsg(e.getMessage());
            responseBean.setExtInfo("Unable to connect to the world!");
            return responseBean;
        }
    }
}
