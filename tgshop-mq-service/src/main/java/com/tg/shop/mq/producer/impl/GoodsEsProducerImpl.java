package com.tg.shop.mq.producer.impl;


import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.mq.config.RabbitMqConfigInfo;
import com.tg.shop.mq.producer.GoodsEsProducer;
import com.tg.shop.mq.service.MessageQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class GoodsEsProducerImpl  implements GoodsEsProducer {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageQueueService messageQueueService;



    @Override
    public boolean send(String goodsId){
        boolean result = false;
        try{
            MessageQueue messageQueue = new MessageQueue();
            String msgId = idGenerator.nextStringId();
            messageQueue.setMsgId(msgId);
            messageQueue.setRefKey(goodsId);
            messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_PRODUCT_ES_INDEX);
            JSONObject jsonData = new JSONObject();
            jsonData.put("goodsId",goodsId);
            messageQueue.setMessageData(jsonData.toJSONString());
            messageQueue.setCreateTime(new Date());
            messageQueue.setCreator("product");
            String sendMsg = JSONObject.toJSONString(messageQueue);
            log.debug("Sender : " + sendMsg);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(msgId);
            int count = messageQueueService.saveMessageQueue(messageQueue);
            this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_PRODUCT,RabbitMqConfigInfo.KEY_PRODUCT_ES_INDEX, sendMsg, correlationData);
            result = true;
        }catch (Exception e){
            log.error("消息异常-商品索引.GoodsEsProducer error. goodsId:"+goodsId+"\n"+e.getMessage());
        }
        return  result;
    }


}
