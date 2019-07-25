package com.tg.shop.mq.consumer;

import com.rabbitmq.client.Channel;
import com.tg.shop.core.mq.RabbitMqConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = RabbitMqConfigInfo.QUEUE_HELLO)
public class HelloConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, Message message){
        try {
            System.out.println("## HelloConsumer:"+msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            log.error("HelloConsumer error.");
        }
    }
}
