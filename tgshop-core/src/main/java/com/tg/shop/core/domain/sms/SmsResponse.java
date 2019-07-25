package com.tg.shop.core.domain.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 大于短信返回结果
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsResponse implements Serializable {

    private Integer result;

    private String requestId;

    private String bizId;

    private String code;

    private String message;
}


