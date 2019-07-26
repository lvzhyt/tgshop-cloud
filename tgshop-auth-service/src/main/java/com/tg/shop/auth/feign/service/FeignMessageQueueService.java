package com.tg.shop.auth.feign.service;

import com.tg.shop.auth.hystrix.FeignMessageQueueServiceHystrix;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.entity.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-mq-producer-service",fallback = FeignMessageQueueServiceHystrix.class)
public interface FeignMessageQueueService {

    /**
     * 发送短信
     * @param smsMessage
     * @return
     */
    @PostMapping("/mq/smsMessage")
    ResultObject sendSmsMessage(@RequestBody SmsMessage smsMessage);
}
