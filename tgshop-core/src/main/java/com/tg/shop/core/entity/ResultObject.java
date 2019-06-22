package com.tg.shop.core.entity;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

/**
 * 请求返回数据
 * @author Administrator
 */
@Data
public class ResultObject implements Serializable {

    /**
     * 返回结果 1 正常 0 异常
     */
    private int result;

    /**
     * 0000为返回正常， 其它code均为请求错误
     */
    private String code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 信息
     */
    private String message;

    public ResultObject() {
        this.result=1;
        this.code = "0000";
        this.message = "";
    }

    public ResultObject(Object data) {
        this();
        this.data = data;
    }


    public ResultObject(String code, String message) {
        this.result = "0000".equals(code)?1:0;
        this.code = code;
        this.message = message;
    }

    public ResultObject(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result =errorCode==ErrorCode.SUCCESS?1:0;
    }

    public ResultObject(ErrorCode errorCode,List<FieldError> fieldErrors) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result =errorCode==ErrorCode.SUCCESS?1:0;
        this.data = fieldErrors;
    }

    public static ResultObject getInstance() {
        return new ResultObject();
    }
    public static ResultObject getInstance(Object data) {
        return new ResultObject(data);
    }

    public static ResultObject getInstance(String message) {
        return new ResultObject(message);
    }

    public static ResultObject getInstance(String code, String message) {
        return new ResultObject(code,message);
    }

    public static ResultObject getInstance(ErrorCode errorCode) {
        return new ResultObject(errorCode);
    }

    public static ResultObject getInstance(ErrorCode errorCode, List<FieldError> fieldErrors) {
        return new ResultObject(errorCode,fieldErrors);
    }
}
