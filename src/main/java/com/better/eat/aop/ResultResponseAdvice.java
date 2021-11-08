package com.better.eat.aop;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.constant.GeneralResponseMessageConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 当Ctrl方法返回值类型不为ResponseBean时
 * 会调用beforeBodyWrite方法进行封装返回值
 * 返回值统一处理
 *
 */
@RestControllerAdvice
public class ResultResponseAdvice<T> implements ResponseBodyAdvice<T> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //returnType    controller方法
        //returnType.getGenericParameterType()  controller方法返回值类型
        //当接口返回值类型不是ResponseBean时 执行beforeBodyWrite
        return !ResponseBean.class.equals(returnType.getGenericParameterType());
    }


    @Override
    public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body || body instanceof ResponseBean){
            return body;
        }
        final ResponseBean<T> result = new ResponseBean<>();
        result.setCode(200).setDesc(GeneralResponseMessageConstant.SUCCESS).setData(body);
        return (T) result;
    }
}
