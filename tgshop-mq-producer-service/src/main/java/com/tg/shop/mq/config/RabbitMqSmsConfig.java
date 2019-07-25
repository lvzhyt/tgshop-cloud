package com.tg.shop.mq.config;

import com.tg.shop.core.mq.RabbitMqConfigInfo;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发送短信 MQ
 * @author Administrator
 */
@Configuration
public class RabbitMqSmsConfig {


    /**
     * 发送短信 exchange
     * @return
     */
    @Bean
    public DirectExchange smsDirectExchange(){
        DirectExchange directExchange = new DirectExchange(RabbitMqConfigInfo.EXCHANGE_SMS,true,false);
        return directExchange;
    }


    /**
     * 发送短信
     * @return
     */
    @Bean
    public Queue smsQueue(){
        return new Queue(RabbitMqConfigInfo.QUEUE_SMS,true,false,false);
    }


    /**
     * 绑定队列
     * KEY_PRODUCT_ES_INDEX ="es.index.product.shop.key";
     * @return
     */
    @Bean
    public Binding bindingSmsQueue(Queue smsQueue,DirectExchange smsDirectExchange){
        return BindingBuilder.bind(smsQueue).to(smsDirectExchange).with(RabbitMqConfigInfo.KEY_SMS_ES_INDEX);
    }

}
