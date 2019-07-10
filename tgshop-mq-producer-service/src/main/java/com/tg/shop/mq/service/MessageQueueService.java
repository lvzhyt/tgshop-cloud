package com.tg.shop.mq.service;

import com.tg.shop.core.domain.mq.entity.MessageQueue;

public interface MessageQueueService {

    int saveMessageQueue(MessageQueue messageQueue);

    int updateMessageQueue(MessageQueue messageQueue);

    int shiftDeleteMessageQueue(String msgId);

    MessageQueue getMessageQueueByMsgId(String msgId);


    /**
     * 消息投递 返回重入队列
     * @param msgId
     * @param maxDeliveryNum
     * @return 是否重入队列
     */
    boolean deliveryAndRequeueAble(String msgId, int maxDeliveryNum);

}
