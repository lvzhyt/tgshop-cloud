package com.tg.shop.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.mq.RabbitMqConfigInfo;
import com.tg.shop.mq.feign.service.FeignGoodsSearchService;
import com.tg.shop.mq.feign.service.FeignTradeService;
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
 * 订单交易 消息消费者
 * @author Administrator
 */
@Slf4j
@Component
public class TradeConsumer {

    @Autowired
    private MessageQueueService messageQueueService;
    @Resource
    private FeignTradeService feignTradeService;


    /**
     * 订单交易  确认订单 扣减库存
     * @param msg
     * @param channel
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfigInfo.QUEUE_TRADE_DEDUCT_STOCK)
    public void handleConfirmOrderStock(String msg, Channel channel, Message message) {
        log.info("handleConfirmOrderStock.message: "+msg);
        MessageQueue messageQueue = JSON.parseObject(msg, MessageQueue.class);
        try {
            JSONObject jsonData = JSON.parseObject(messageQueue.getMessageData());
            String orderId = jsonData.getString("orderId");
            ResultObject resultObject = feignTradeService.confirmOrderStock(orderId);
            boolean success = false;
            if(resultObject.getResult()==0){
                boolean stockLess  = resultObject.getCode().equals(ErrorCode.TRADE_NOT_ENOUGH_INVENTORY.getCode());
                boolean stockLessCanceled = false;
                // 缺少库存 确认订单失败 取消订单
                if(stockLess){
                    ResultObject rs = feignTradeService.cancelConfirmFailedOrder(orderId);
                    stockLessCanceled = resultObject.getResult()==1;
                    if(rs.getResult()==0){
                        log.error("缺少库存 确认订单失败 取消订单 失败.cancelConfirmFailedOrder() "+JSONObject.toJSONString(resultObject));
                    }else {
                        success = true;
                    }
                }
                // 非缺少库存 或者 取消订单失败 则重试
                if(!stockLess || !stockLessCanceled){
                    boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
                    log.error("TradeDeductStockConsumer.handleConfirmOrderStock() "+JSONObject.toJSONString(resultObject));
                    return;
                }
            }else {
                success = true;
            }
            if(success){
                messageQueue.setDeliveryTag(message.getMessageProperties().getDeliveryTag());
                messageQueue.setMsgState(1);
                messageQueue.setDeliveryTime(new Date());
                messageQueueService.updateMessageQueue(messageQueue);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }
        } catch (Exception e) {
            try {
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
            } catch (IOException ex) {
                log.error("订单交易 确认订单扣减库存,消息重试异常.basicNack"+ex.getMessage(),ex);
            }
            log.error("订单交易 确认订单扣减库存 异常."+e.getMessage(),e);
        }

    }


    /**
     * 订单交易 取消订单 恢复库存
     * @param msg
     * @param channel
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfigInfo.QUEUE_TRADE_INCREASE_STOCK)
    public void handleCancelOrderStock(String msg, Channel channel, Message message) {
        log.info("handleCancelOrderStock.message: "+msg);
        MessageQueue messageQueue = JSON.parseObject(msg, MessageQueue.class);
        try {
            JSONObject jsonData = JSON.parseObject(messageQueue.getMessageData());
            String orderId = jsonData.getString("orderId");
            ResultObject resultObject = feignTradeService.cancelOrderStock(orderId);
            if(resultObject.getResult()==1){
                messageQueue.setDeliveryTag(message.getMessageProperties().getDeliveryTag());
                messageQueue.setMsgState(1);
                messageQueue.setDeliveryTime(new Date());
                messageQueueService.updateMessageQueue(messageQueue);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else{
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
                log.error("TradeDeductStockConsumer.handleCancelOrderStock() "+JSONObject.toJSONString(resultObject));
            }
        } catch (Exception e) {
            try {
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
            } catch (IOException ex) {
                log.error("订单交易 恢复库存,消息重试异常.basicNack"+ex.getMessage(),ex);
            }
            log.error("订单交易 恢复库存 异常."+e.getMessage(),e);
        }

    }


    /**
     * 拆单
     * @param msg
     * @param channel
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfigInfo.QUEUE_TRADE_DISASSEMBLE_ORDER)
    public void handleDisassembleOrder(String msg, Channel channel, Message message) {
        log.info("handleDisassembleOrder.message: "+msg);
        MessageQueue messageQueue = JSON.parseObject(msg, MessageQueue.class);
        try {
            JSONObject jsonData = JSON.parseObject(messageQueue.getMessageData());
            String orderId = jsonData.getString("orderId");
            ResultObject resultObject = feignTradeService.disassembleOrder(orderId);
            if(resultObject.getResult()==1){
                messageQueue.setDeliveryTag(message.getMessageProperties().getDeliveryTag());
                messageQueue.setMsgState(1);
                messageQueue.setDeliveryTime(new Date());
                messageQueueService.updateMessageQueue(messageQueue);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else{
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
                log.error("TradeDeductStockConsumer.handleDisassembleOrder "+JSONObject.toJSONString(resultObject));
            }
        } catch (Exception e) {
            try {
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
            } catch (IOException ex) {
                log.error("订单交易 拆单,消息重试异常.basicNack"+ex.getMessage(),ex);
            }
            log.error("订单交易 拆单 异常."+e.getMessage(),e);
        }

    }
}
