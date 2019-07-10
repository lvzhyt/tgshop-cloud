package com.tg.shop.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqHelloConfig {

//    public static final String EXCHANGE_HELLO = "hello.exchange";
//    public static final String QUEUE_HELLO = "hello.queue";
//    public static final String KEY_HELLO = "hello.key";

    @Bean
    public DirectExchange helloDirectExchange(){
        DirectExchange directExchange = new DirectExchange(RabbitMqConfigInfo.EXCHANGE_HELLO,true,false);
        return directExchange;
    }


    @Bean
    public Queue helloQueue(){
        return new Queue(RabbitMqConfigInfo.QUEUE_HELLO,true,false,false);
    }


    @Bean
    public Binding bindingHelloQueue(Queue helloQueue, DirectExchange helloDirectExchange){
        return BindingBuilder.bind(helloQueue).to(helloDirectExchange).with(RabbitMqConfigInfo.KEY_HELLO);
    }


}
