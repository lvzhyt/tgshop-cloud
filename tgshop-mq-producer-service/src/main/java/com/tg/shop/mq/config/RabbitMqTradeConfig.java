package com.tg.shop.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTradeConfig {


    /**
     * 交易 exchange
     * @return
     */
    @Bean
    public DirectExchange tradeDirectExchange(){
        DirectExchange directExchange = new DirectExchange(RabbitMqConfigInfo.EXCHANGE_TRADE,true,false);
        return directExchange;
    }


    /**
     * 扣减库存队列
     * @return
     */
    @Bean
    public Queue deductStockQueue(){
        return new Queue(RabbitMqConfigInfo.QUEUE_TRADE_DEDUCT_STOCK,true,false,false);
    }

    /**
     * 增加库存队列
     * @return
     */
    @Bean
    public Queue increaseStockQueue(){
        return new Queue(RabbitMqConfigInfo.QUEUE_TRADE_INCREASE_STOCK,true,false,false);
    }


    @Bean
    public Binding bindingDeductStockQueue(Queue deductStockQueue,DirectExchange tradeDirectExchange){
        return BindingBuilder.bind(deductStockQueue).to(tradeDirectExchange).with(RabbitMqConfigInfo.KEY_TRADE_DEDUCT_STOCK);
    }

    @Bean
    public Binding bindingIncreaseStockQueue(Queue increaseStockQueue,DirectExchange tradeDirectExchange){
        return BindingBuilder.bind(increaseStockQueue).to(tradeDirectExchange).with(RabbitMqConfigInfo.KEY_TRADE_INCREASE_STOCK);
    }

}
