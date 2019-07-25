package com.tg.shop.core.domain.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SmsMessage {

    /**
     * 模板
     */
    private String templateCode;

    /**
     * 手机
     */
    private String phoneNumbers;

    /**
     * 签名
     */
    private String signName;

    /**
     * 模板参数
     */
    private String templateParam;

    /**
     * 回执ID
     */
    private String outId;

}
