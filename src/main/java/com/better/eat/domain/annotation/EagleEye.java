package com.better.eat.domain.annotation;

import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author andy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Document
public @interface EagleEye {
    /**
     * description for interface
     * @return description
     */
    String desc() default "";
}
