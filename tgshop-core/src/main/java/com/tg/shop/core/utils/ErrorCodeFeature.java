package com.tg.shop.core.utils;

public enum ErrorCodeFeature {
    VALID_BIND_ERROR("20000","绑定数据校验失败"),
    DATA_UNIQUE_ERROR("30001","不满足数据库唯一性");

    private String code;
    private String value;

    private ErrorCodeFeature(String code, String value){
        this.code = code;
        this.value = value;
    }

    public String getCode(){
        return this.code;
    }

    public String getValue(){
        return this.value;
    }

}

