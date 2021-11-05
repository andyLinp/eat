package com.better.eat.domain;

import com.better.eat.domain.constant.GeneralResponseMessageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统 统一返回值
 * @author andy
 */
@ApiModel
@Data
@Accessors(chain = true)
public class ResponseBean<T> {

    @ApiModelProperty(value = "返回码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "返回码描述", example = "ok")
    private String desc;

    @ApiModelProperty(value = "响应时间戳", example = "2021-11-04 14:37:11")
    private Date timestamp = new Date();

    @ApiModelProperty(value = "返回结果")
    private T data;

    public static <T> ResponseBean<T> success(T t) {
        ResponseBean<T> result = new ResponseBean<>();
        result.code = 200;
        result.desc = GeneralResponseMessageConstant.SUCCESS;
        result.data = t;
        return result;
    }

    public static ResponseBean<Object> failed(String message) {
        ResponseBean<Object> result = new ResponseBean<>();
        result.code = 500;
        result.desc = message;
        return result;
    }


}
