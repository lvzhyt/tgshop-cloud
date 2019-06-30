package com.tg.shop.core.entity;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum ErrorCode {

    /**
     * success
     */
    SUCCESS("0000", "success"),


    REQUEST_ERROR("400", "请求错误"),
    UNAUTHORIZED("401", "未授权"),
    NOT_ACCESSIBLE("403", "不可访问"),
    METHOD_NOT_ALLOWED("405", "方法不被允许"),
    UNSUPPORTED_MEDIA_TYPE("415", "不支持当前媒体类型"),

    HYSTRIX_SERVICE_ERROR("0004", "服务熔断错误"),

    REQUEST_PARAM_ERROR("1000", "请求参数错误"),

    TOKEN_IS_EMPTY("1001","您的登录令牌为空，请登录"),
    TOKEN_LOSE_EFFICACY("1002","您的登录令牌已失效，请重新登录"),
    LOGIN_USER_NOT_EXISTS("1003","用户不存在"),
    LOGIN_PASSWORD_ERROR("1004","登录密码错误"),

    EMPTY_DATA_ERROR("2001", "查询为空"),


    SERVER_ERROR("9999", "system error");



    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCode getByCode(String code) {

        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }

        return null;
    }
}
