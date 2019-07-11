package com.tg.shop.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.mq.config.RabbitMqConfigInfo;
import com.tg.shop.mq.feign.service.FeignGoodsSearchService;
import com.tg.shop.mq.service.MessageQueueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * 商品索引 消息消费者
 * @author Administrator
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConfigInfo.QUEUE_PRODUCT_ES)
public class GoodsEsConsumer {

    @Autowired
    private MessageQueueService messageQueueService;
    @Resource
    private FeignGoodsSearchService feignGoodsSearchService;


    @RabbitHandler
    public void process(String msg, Channel channel, Message message) {
        log.debug("GoodsEsConsumer.message: "+msg);
        MessageQueue messageQueue = JSON.parseObject(msg, MessageQueue.class);
        try {
            JSONObject jsonData = JSON.parseObject(messageQueue.getMessageData());
            String goodsId = jsonData.getString("goodsId");
            feignGoodsSearchService.updateGoodsSearchIndex(goodsId);
            messageQueue.setDeliveryTag(message.getMessageProperties().getDeliveryTag());
            messageQueue.setMsgState(1);
            messageQueue.setDeliveryTime(new Date());
            messageQueueService.updateMessageQueue(messageQueue);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            try {
                boolean reQueue = messageQueueService.deliveryAndRequeueAble(messageQueue.getMsgId(),RabbitMqConfigInfo.DEFAULT_MAX_DELIVERY_NUM);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
            } catch (IOException ex) {
                log.error("商品索引异常.basicNack"+ex.getMessage(),ex);
            }
            log.error("商品索引异常."+e.getMessage(),e);
        }

    }
}
