package com.better.eat.domain;

import lombok.Data;

@Data
public class ResponseBean<T> {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 如果suceess为true, 则有result ,否则没有
     */
    private T result;
    /**
     * 如果sucess 为false 则有errorMsg.否则没有
     */
    private String errorMsg;
    /**
     * 附加信息
     */
    private Object extInfo;

}
