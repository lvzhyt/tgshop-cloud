package com.tg.shop.mq.producer.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.mq.RabbitMqConfigInfo;
import com.tg.shop.mq.producer.SmsProducer;
import com.tg.shop.mq.service.MessageQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 发送短信
 * @author Administrator
 */
@Slf4j
@Service
public class SmsProducerImpl implements SmsProducer {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageQueueService messageQueueService;


    @Override
    public boolean send(SmsMessage smsMessage) {
        boolean result = false;
        try{
            MessageQueue messageQueue = new MessageQueue();
            String msgId = idGenerator.nextStringId();
            messageQueue.setMsgId(msgId);
            messageQueue.setRefKey(msgId);
            messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_SMS);
            messageQueue.setMessageData(JSONObject.toJSONString(smsMessage));
            messageQueue.setCreateTime(new Date());
            messageQueue.setCreator("sms");
            String sendMsg = JSONObject.toJSONString(messageQueue);
            log.debug("Sender : " + sendMsg);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(msgId);
            int count = messageQueueService.saveMessageQueue(messageQueue);
            this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_SMS,RabbitMqConfigInfo.KEY_SMS_ES_INDEX, sendMsg, correlationData);
            result = true;
        }catch (Exception e){
            log.error("消息异常-发送短信.EsSmsProducerImpl error. smsMessage:"+ JSON.toJSONString(smsMessage),e);
        }
        return  result;

    }
}
