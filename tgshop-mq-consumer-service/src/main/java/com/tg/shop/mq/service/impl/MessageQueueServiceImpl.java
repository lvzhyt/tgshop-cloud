package com.tg.shop.mq.service.impl;

import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.domain.mq.entity.MessageQueueLog;

import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.mq.mapper.MessageQueueLogMapper;
import com.tg.shop.mq.mapper.MessageQueueMapper;
import com.tg.shop.mq.service.MessageQueueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MessageQueueServiceImpl implements MessageQueueService {

    @Resource
    private MessageQueueMapper messageQueueMapper;
    @Resource
    private MessageQueueLogMapper messageQueueLogMapper;
    @Autowired
    private IdGenerator idGenerator;

    @Override
    public int saveMessageQueue(MessageQueue messageQueue) {
        int count = messageQueueMapper.insertSelective(messageQueue);
        if(count>0){
            saveMqLog(messageQueue);
        }

        return count;
    }

    @Override
    public int updateMessageQueue(MessageQueue messageQueue) {
        Assert.notNull(messageQueue.getMsgId(),"msgId must be not null");
        int count = messageQueueMapper.updateByPrimaryKeySelective(messageQueue);
        if(count>0){
            saveMqLog(messageQueue);
        }

        return count;
    }


    @Override
    public int shiftDeleteMessageQueue(String msgId) {
        MessageQueue messageQueue = getMessageQueueByMsgId(msgId);
        messageQueue.setIsDel(BaseEntityInfo.STATE_DELETE);
        int count = messageQueueMapper.deleteByPrimaryKey(msgId);
        if(count>0){
            saveMqLog(messageQueue);
        }
        return 0;
    }

    @Override
    public MessageQueue getMessageQueueByMsgId(String msgId) {
        return messageQueueMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public boolean deliveryAndRequeueAble(String msgId, int maxDeliveryNum) {
        MessageQueue record = messageQueueMapper.selectByPrimaryKey(msgId);
        if(record==null){
            return false;
        }
        boolean reQueue = record.getDeliveryNum()<maxDeliveryNum;
        if(record.getDeliveryNum()<maxDeliveryNum){
            record.setDeliveryNum(record.getDeliveryNum()+1);
            record.setDeliveryTime(new Date());
            record.setModifier("mq");
            record.setModifyTime(new Date());
            reQueue = true;
            messageQueueMapper.updateByPrimaryKeySelective(record);
            saveMqLog(record);
        }
        return reQueue;
    }

    private int saveMqLog(MessageQueue messageQueue) {
        MessageQueueLog mqLog = new MessageQueueLog();
        BeanUtils.copyProperties(messageQueue, mqLog);
        mqLog.setTbId(idGenerator.nextStringId());
        mqLog.setCreateTime(new Date());
        return messageQueueLogMapper.insertSelective(mqLog);
    }
}
