package com.tg.shop.mq.sms.aliyun.service;


import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.domain.sms.SmsResponse;

public interface SmsService {


    SmsResponse sendMessage(SmsMessage smsMessage);
}
