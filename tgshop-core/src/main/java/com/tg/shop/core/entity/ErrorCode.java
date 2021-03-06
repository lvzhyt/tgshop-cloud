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
    REGISTER_USER_ALREADY_EXISTS("1005","用户已存在"),
    REGISTER_CODE_ERROR("1006","验证码错误"),
    REQUEST_REPEAT_ERROR("1010","重复提交"),
    /**
     * 权限不足
     */
    TOKEN_AUTH_POWER_ERROR("1005","权限不足"),


    MQ_TRADE_CONFIRM_ORDER_ERROR("2301", "确认订单消息错误"),
    MQ_TRADE_CANCEL_ORDER_ERROR("2302", "取消订单消息错误"),
    MQ_TRADE_DISASSEMBLE_ORDER_ERROR("2303", "拆单单消息错误"),

    TRADE_NOT_ENOUGH_INVENTORY("3001", "库存不足"),
    TRADE_CONFIRM_ORDER_OUT_OF_TIME_ERROR("3002", "确认订单超时"),
    TRADE_DISASSEMBLE_ORDER_ERROR("3003", "拆单错误"),

    EMPTY_DATA_ERROR("6001", "查询为空"),
    UPDATE_ZERO_ROW_ERROR("6002", "更新失败"),

    HYSTRIX_ERROR("8001", "服务熔断"),


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
