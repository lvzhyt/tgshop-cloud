package com.tg.shop.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.domain.sms.SmsInfo;
import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.domain.sms.SmsResponse;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.mq.RabbitMqConfigInfo;
import com.tg.shop.mq.service.MessageQueueService;
import com.tg.shop.mq.sms.aliyun.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 发送短信 消息消费者
 * @author Administrator
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConfigInfo.QUEUE_SMS)
public class SmsConsumer {

    @Autowired
    private MessageQueueService messageQueueService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RabbitHandler
    public void process(String msg, Channel channel, Message message) {
        log.debug("SmsConsumer.message: "+msg);
        MessageQueue messageQueue = JSON.parseObject(msg, MessageQueue.class);
        try {
            SmsMessage smsMessage = JSONObject.parseObject(messageQueue.getMessageData(),SmsMessage.class);
            System.out.println("发送短信 "+smsMessage);
            SmsResponse smsResponse = smsService.sendMessage(smsMessage);
            if(smsResponse.getResult()==0){
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
                log.error("SmsConsumer. "+JSONObject.toJSONString(smsResponse));
                return;
            }
            // 注册码
            if(SmsInfo.TEMPLATE_REGISTER_CODE.equals(smsMessage.getTemplateCode())){
                JSONObject jsonObject = JSONObject.parseObject(smsMessage.getTemplateParam());
                String key = "sms-"+smsMessage.getPhoneNumbers();
                String val = jsonObject.getString("code");
                stringRedisTemplate.opsForValue().set(key,val,300, TimeUnit.SECONDS);
            }

            messageQueue.setDeliveryTag(message.getMessageProperties().getDeliveryTag());
            messageQueue.setMsgState(1);
            messageQueue.setDeliveryTime(new Date());
            messageQueueService.updateMessageQueue(messageQueue);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            try {
                boolean reQueue = messageQueueService.requeueAble(messageQueue.getMsgId());
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,reQueue);
            } catch (IOException ex) {
                log.error("发送短信,消息重试异常.basicNack"+ex.getMessage(),ex);
            }
            log.error("发送短信 异常."+e.getMessage(),e);
        }

    }
}
