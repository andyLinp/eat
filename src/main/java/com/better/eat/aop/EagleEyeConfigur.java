package com.better.eat.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
@Aspect
public class EagleEyeConfigur {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @After("@annotation(com.better.eat.domain.annotation.EagleEye)")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("class:"+name);
    }
}
