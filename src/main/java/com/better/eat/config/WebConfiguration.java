package com.better.eat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 处理spring框架提供的转换器
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //方法一:将Json处理的转换器放到第一位,使得先让json转换器处理返回值,这样String转换器就处理不了
        converters.add(0,new MappingJackson2HttpMessageConverter());
        //方法二:移除String转换器
        //converters.removeIf(httpMessageConverter->httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}
