package com.tg.shop.mq.controller;

import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.producer.SmsProducer;
import com.tg.shop.mq.producer.GoodsEsProducer;
import com.tg.shop.mq.producer.HelloProducer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageQueueController {

    @Autowired
    private HelloProducer helloProducer;
    @Autowired
    private GoodsEsProducer goodsEsProducer;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SmsProducer smsProducer;


    @GetMapping("/mq/sendHelloMq")
    public ResultObject sendHelloMq(@RequestParam String message){
        helloProducer.send(message);
        return new ResultObject();
    }


    /**
     * 商品索引
     * @param goodsId
     * @return
     */
    @GetMapping("/mq/goodsEs")
    public ResultObject goodsElasticSearch(@RequestParam String goodsId){
        boolean isSend = goodsEsProducer.send(goodsId);
        if(!isSend){
            return new ResultObject<>("6001","商品索引,发送消息失败");
        }
        return new ResultObject<>();
    }

    @ApiOperation("发送短信")
    @PostMapping("/mq/smsMessage")
    public ResultObject sendSmsMessage(@RequestBody SmsMessage smsMessage){
        boolean success = smsProducer.send(smsMessage);
        ResultObject resultObject = success?new ResultObject():new ResultObject("20010","发送短信失败");
        return resultObject;
    }


}
