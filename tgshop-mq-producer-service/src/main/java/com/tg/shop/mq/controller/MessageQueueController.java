package com.tg.shop.mq.controller;

import com.tg.shop.core.domain.sms.SmsMessage;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.producer.SmsProducer;
import com.tg.shop.mq.producer.GoodsEsProducer;
import com.tg.shop.mq.producer.HelloProducer;
import com.tg.shop.mq.producer.TradeProducer;
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
    @Autowired
    private TradeProducer tradeProducer;


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



    /**
     * 发送消息 确认订单 扣减库存
     */
    @ApiOperation("发送消息 确认订单 扣减库存")
    @GetMapping("/mq/sendConfirmOrderStock")
    public ResultObject sendConfirmOrderStock(@RequestParam String orderId){
        boolean success = tradeProducer.sendConfirmOrderStock(orderId);
        ResultObject resultObject = success?new ResultObject():new ResultObject(ErrorCode.MQ_TRADE_CONFIRM_ORDER_ERROR);
        return resultObject;
    }

    /**
     * 发送消息 取消订单
     * 恢复库存
     */
    @ApiOperation("发送消息 取消订单 恢复库存")
    @GetMapping("/mq/sendCancelOrderStock")
    public ResultObject sendCancelOrderStock(@RequestParam String orderId){
        boolean success = tradeProducer.sendCancelOrderStock(orderId);
        ResultObject resultObject = success?new ResultObject():new ResultObject(ErrorCode.MQ_TRADE_CANCEL_ORDER_ERROR);
        return resultObject;
    }

    /**
     * 拆单
     * 按店铺拆单
     */
    @ApiOperation("发送消息 取消订单 恢复库存")
    @GetMapping("/mq/sendDisassembleOrder")
    public ResultObject sendDisassembleOrder(@RequestParam String orderId){
        return tradeProducer.sendDisassembleOrder(orderId);
    }

}
