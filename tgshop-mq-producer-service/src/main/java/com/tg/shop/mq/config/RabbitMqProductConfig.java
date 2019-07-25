package com.tg.shop.mq.config;

import com.tg.shop.core.mq.RabbitMqConfigInfo;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqProductConfig {


    /**
     * 商品 exchange
     * @return
     */
    @Bean
    public DirectExchange productDirectExchange(){
        DirectExchange directExchange = new DirectExchange(RabbitMqConfigInfo.EXCHANGE_PRODUCT,true,false);
        return directExchange;
    }


    /**
     * 商品搜索队列
     * @return
     */
    @Bean
    public Queue productEsQueue(){
        return new Queue(RabbitMqConfigInfo.QUEUE_PRODUCT_ES,true,false,false);
    }


    /**
     * 绑定商品队列
     * KEY_PRODUCT_ES_INDEX ="es.index.product.shop.key";
     * @param productEsQueue
     * @param productDirectExchange
     * @return
     */
    @Bean
    public Binding bindingProductEsQueue(Queue productEsQueue,DirectExchange productDirectExchange){
        return BindingBuilder.bind(productEsQueue).to(productDirectExchange).with(RabbitMqConfigInfo.KEY_PRODUCT_ES_INDEX);
    }

}
