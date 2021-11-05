package com.better.eat.ctrl;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.annotation.EagleEye;
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
    @EagleEye
    @GetMapping
    public Object helloWorld(String world){
        ResponseBean rb = new ResponseBean();
        try{
           return service.getWorld(world);
        }catch(RuntimeException e){
            rb.setCode(501);
            rb.setDesc(e.getMessage());
            return rb;
        }catch(Exception e){
            rb.setCode(500);
            rb.setDesc(e.getMessage());
            return rb;
        }
    }
}
