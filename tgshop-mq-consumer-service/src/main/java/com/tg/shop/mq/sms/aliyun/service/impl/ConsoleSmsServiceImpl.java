package com.tg.shop.mq.sms.aliyun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.domain.sms.SmsResponse;
import com.tg.shop.mq.sms.aliyun.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsoleSmsServiceImpl implements SmsService {

    @Override
    public SmsResponse sendMessage(SmsMessage smsMessage) {
        String msg = "ConsoleSmsServiceImpl sendMessage:"+ JSONObject.toJSONString(smsMessage);
        System.out.println(msg);
        log.info(msg);
        SmsResponse response = new SmsResponse(1, "1","1", "ok", "");

        return response;
    }


}
