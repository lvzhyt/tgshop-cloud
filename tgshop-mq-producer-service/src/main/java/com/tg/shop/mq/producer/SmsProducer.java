package com.tg.shop.mq.producer;

import com.tg.shop.core.domain.sms.SmsMessage;

public interface SmsProducer {

    /**
     * 发送短信
     * @param smsMessage
     */
    boolean send(SmsMessage smsMessage);

}
