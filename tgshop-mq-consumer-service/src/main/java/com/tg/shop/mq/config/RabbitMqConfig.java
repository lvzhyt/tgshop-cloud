package com.tg.shop.mq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {


    @Autowired
    private MqConfirmCallback mqConfirmCallback;
    @Autowired
    private MqReturnCallback mqReturnCallback;


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 消息发送失败回调
        rabbitTemplate.setReturnCallback(mqReturnCallback);
        // 消息确认回调
        rabbitTemplate.setConfirmCallback(mqConfirmCallback);
        return rabbitTemplate;
    }
}
