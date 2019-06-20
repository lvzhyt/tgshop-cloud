package com.tg.shop.manager.seller.request;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class ErrorInfoParam {

    private String type;
    private Integer code;
    private String message;
    private String url;
}
