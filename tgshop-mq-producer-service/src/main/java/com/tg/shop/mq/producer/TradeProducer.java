package com.tg.shop.mq.producer;

import com.tg.shop.core.entity.ResultObject;

public interface TradeProducer {

    /**
     * 发送消息 确认订单
     * 扣减库存
     */
    boolean sendConfirmOrderStock(String orderId);

    /**
     * 发送消息 取消订单
     * 恢复库存
     */
    boolean sendCancelOrderStock(String orderId);

    ResultObject sendDisassembleOrder(String orderId);
}
