package com.tg.shop.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 执行返回状态
 * @Author: tg
 * @Date: 2019/3/23 10:24
 */
@Data
public class ResultState implements Serializable {

    public static final int MESSAGE_TYPE_SUCCESS = 1;
    public static final int MESSAGE_TYPE_ERROR = 2;

    public static final String STATE_OK="0";

    public static final String DEFAULT_SUCCESS_MESSAGE="操作成功";

    public static final String SQL_EFFECT_ZERO_MESSAGE="影响数据条数0";

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误消息
     */
    private String errorMessage;

    /**
     * 成功消息
     */
    private String successMessage;

    /**
     * 数据
     */
    private Object data;

    public ResultState() {
        this.errorCode = STATE_OK;
        this.successMessage = DEFAULT_SUCCESS_MESSAGE;
    }
    public ResultState(int resultCount) {
        if(resultCount==0){
            this.errorCode = "60001";
            this.errorMessage = SQL_EFFECT_ZERO_MESSAGE;
        }else {
            this.errorCode = STATE_OK;
            this.successMessage = DEFAULT_SUCCESS_MESSAGE;
        }
    }

    public ResultState(int type,String message) {
        if(type==MESSAGE_TYPE_SUCCESS){
            this.errorCode = STATE_OK;
            this.successMessage = message;
        }else{
            this.errorCode = message;
            this.errorMessage = message;
        }
    }

    public ResultState(Object data) {
        this.errorCode = STATE_OK;
        this.successMessage = DEFAULT_SUCCESS_MESSAGE;
        this.data = data;
    }
    public ResultState(String successMessage,Object data) {
        this.errorCode = STATE_OK;
        this.successMessage = successMessage;
        this.data = data;
    }
}
