package com.tg.shop.mq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发送失败回调
 */
@Component
public class MqReturnCallback implements RabbitTemplate.ReturnCallback {


    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
    }
}
