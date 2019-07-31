package com.tg.shop.mq.producer.impl;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.mq.RabbitMqConfigInfo;
import com.tg.shop.mq.producer.TradeProducer;
import com.tg.shop.mq.service.MessageQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TradeProducerImpl implements TradeProducer {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private MessageQueueService messageQueueService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean sendConfirmOrderStock(String orderId) {
        boolean result = false;
        try{
            MessageQueue messageQueue = new MessageQueue();
            String msgId = idGenerator.nextStringId();
            messageQueue.setMsgId(msgId);
            messageQueue.setRefKey(orderId);
            messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_TRADE_DEDUCT_STOCK);
            JSONObject jsonData = new JSONObject();
            jsonData.put("orderId",orderId);
            messageQueue.setMessageData(jsonData.toJSONString());
            messageQueue.setCreateTime(new Date());
            messageQueue.setCreator("Trade");
            String sendMsg = JSONObject.toJSONString(messageQueue);
            log.debug("sendConfirmOrderStock : " + sendMsg);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(msgId);
            int count = messageQueueService.saveMessageQueue(messageQueue);
            this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_TRADE,RabbitMqConfigInfo.KEY_TRADE_DEDUCT_STOCK, sendMsg, correlationData);
            result = true;
        }catch (Exception e){
            log.error("消息异常-确认订单 扣减库存.GoodsEsProducer error. orderId:"+orderId+"\n"+e.getMessage());
        }
        return  result;
    }

    @Override
    public boolean sendCancelOrderStock(String orderId) {
        boolean result = false;
        try{
            MessageQueue messageQueue = new MessageQueue();
            String msgId = idGenerator.nextStringId();
            messageQueue.setRefKey(orderId);
            messageQueue.setMsgId(msgId);
            messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_TRADE_INCREASE_STOCK);
            JSONObject jsonData = new JSONObject();
            jsonData.put("orderId",orderId);
            messageQueue.setMessageData(jsonData.toJSONString());
            messageQueue.setCreator("Trade");
            messageQueue.setCreateTime(new Date());
            String sendMsg = JSONObject.toJSONString(messageQueue);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(msgId);
            log.debug("sendCancelOrderStock : " + sendMsg);
            int count = messageQueueService.saveMessageQueue(messageQueue);
            this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_TRADE,RabbitMqConfigInfo.KEY_TRADE_INCREASE_STOCK, sendMsg, correlationData);
            result = true;
        }catch (Exception e){
            log.error("消息异常-取消订单 恢复库存.GoodsEsProducer error. orderId:"+orderId+"\n"+e.getMessage());
        }
        return  result;
    }

    @Override
    public ResultObject sendDisassembleOrder(String orderId) {

        try{
            MessageQueue messageQueue = new MessageQueue();
            String msgId = idGenerator.nextStringId();
            messageQueue.setRefKey(orderId);
            messageQueue.setMsgState(RabbitMqConfigInfo.MQ_TYPE_TRADE_DISASSEMBEL_ORDER);
            messageQueue.setMsgId(msgId);
            JSONObject jsonData = new JSONObject();
            jsonData.put("orderId",orderId);
            messageQueue.setMessageData(jsonData.toJSONString());
            messageQueue.setCreateTime(new Date());
            messageQueue.setCreator("Trade");
            String sendMsg = JSONObject.toJSONString(messageQueue);
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(msgId);
            log.debug("sendDisassembleOrder : " + sendMsg);
            int count = messageQueueService.saveMessageQueue(messageQueue);
            this.rabbitTemplate.convertAndSend(RabbitMqConfigInfo.EXCHANGE_TRADE,RabbitMqConfigInfo.KEY_TRADE_DISASSEMBLE_ORDER, sendMsg, correlationData);
            return new ResultObject();
        }catch (Exception e){
            log.error("消息异常-拆单.sendDisassembleOrder error. orderId:"+orderId+"\n"+e.getMessage());
        }
        return new ResultObject(ErrorCode.MQ_TRADE_DISASSEMBLE_ORDER_ERROR);
    }
}
