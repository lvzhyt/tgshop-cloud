package com.tg.shop.mq.producer.impl;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.sms.SmsInfo;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.mq.producer.SmsProducer;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math.util.MathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsProducerImplTest {

    @Autowired
    private SmsProducer smsProducer;

//    @Test
    public void send(){
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setPhoneNumbers("18610239332");
        smsMessage.setTemplateCode(SmsInfo.TEMPLATE_REGISTER_CODE);
        smsMessage.setSignName(SmsInfo.SIGN_NAME);
        String code = String.valueOf(RandomUtils.nextInt(1000,9999));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        smsMessage.setTemplateParam(jsonObject.toJSONString());

        boolean success = smsProducer.send(smsMessage);
        System.out.println("smsProducer.send() success:"+success);

    }

}