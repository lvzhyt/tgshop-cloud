package com.tg.shop.mq.producer.impl;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.mq.config.RabbitMqConfigInfo;
import com.tg.shop.mq.producer.HelloProducer;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class HelloProducerImpl implements HelloProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public void send(String msg){
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.setMsgId(idGenerator.nextStringId());
        messageQueue.setRefKey(idGenerator.nextStringId());
        messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_HELLO);
        JSONObject jsonData = new JSONObject();
        jsonData.put("msg",msg);
        messageQueue.setMessageData(jsonData.toJSONString());
        messageQueue.setCreateTime(new Date());
        messageQueue.setCreator("hello");
//        messageQueue.setDeliveryTag();
        String sendMsg = JSONObject.toJSONString(messageQueue);
        System.out.println("Sender : " + sendMsg);
//        this.rabbitTemplate.convertAndSend(RabbitMqHelloConfig.EXCHANGE_HELLO,RabbitMqHelloConfig.KEY_HELLO, sendMsg);

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());

        // exchange：交换机
        // routingKey：路由键
        // message：消息体内容
        // correlationData：消息唯一ID
        this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_HELLO,RabbitMqConfigInfo.KEY_HELLO, sendMsg, correlationData);

    }

    public void send2(){
        String sendMsg = "hello send 2";
        System.out.println("Sender : " + sendMsg);

        this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_HELLO+"2",RabbitMqConfigInfo.KEY_HELLO+"2", sendMsg);
    }
}
