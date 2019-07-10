package com.tg.shop.mq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息确认回调
 */
@Component
public class MqConfirmCallback implements RabbitTemplate.ConfirmCallback {


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println("MqConfirmCallback confirm ");
//        System.out.println(JSON.toJSONString(correlationData)+ " ack:"+ack+" cause:"+cause);
    }
}
