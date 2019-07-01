package com.tg.shop.zuul.seller.fallback;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum ZuulErrorCode {

    /**
     * success
     */
    SUCCESS("0000", "success"),

    PRODUCT_SERVICE_ERROR("0001","PRODUCT_SERVICE_ERROR");



    private String code;

    private String message;

    ZuulErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ZuulErrorCode getByCode(String code) {

        for (ZuulErrorCode errorCode : ZuulErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }

        return null;
    }
}
