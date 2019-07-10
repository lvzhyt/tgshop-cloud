package com.tg.shop.mq.consumer;

import com.rabbitmq.client.Channel;
import com.tg.shop.mq.config.RabbitMqConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = RabbitMqConfigInfo.QUEUE_TRADE_DEDUCT_STOCK)
public class StockDeductConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message){
        try {
            System.out.println("## StockDeductConsumer:"+msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");
        } catch (IOException e) {
            log.error("StockDeductConsumer error.");
        }
    }
}
