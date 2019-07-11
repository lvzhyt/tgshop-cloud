package com.tg.shop.mq.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.mq.entity.MessageQueue;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.producer.GoodsEsProducer;
import com.tg.shop.mq.producer.HelloProducer;
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

}
