package com.tg.shop.core.entity;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

/**
 * 请求返回数据
 * @author Administrator
 */
public class ResultObject<T> implements Serializable {

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
    private T data;

    /**
     * 信息
     */
    private String message;


    public ResultObject() {
        this.result=1;
        this.code = "0000";
        this.message = "";
    }

    public ResultObject(T data) {
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

    public ResultObject(ErrorCode errorCode,T fieldErrors) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.result =errorCode==ErrorCode.SUCCESS?1:0;
        this.data = fieldErrors;
    }


    public boolean isSuccess(){
        return this.result==1;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
