package com.tg.shop.trade.feign.service;

import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.hystrix.FeignMessageQueueServiceHystrix;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-mq-producer-service",fallback = FeignMessageQueueServiceHystrix.class)
public interface FeignMessageQueueService {


    /**
     * 发送消息 确认订单 扣减库存
     */
    @ApiOperation("发送消息 确认订单 扣减库存")
    @GetMapping("/mq/sendConfirmOrderStock")
    ResultObject sendConfirmOrderStock(@RequestParam String orderId);

    /**
     * 发送消息 取消订单
     * 恢复库存
     */
    @ApiOperation("发送消息 取消订单 恢复库存")
    @GetMapping("/mq/sendCancelOrderStock")
    ResultObject sendCancelOrderStock(@RequestParam String orderId);


    /**
     * 拆单
     * 按店铺拆单
     */
    @ApiOperation("发送消息 取消订单 恢复库存")
    @GetMapping("/mq/sendDisassembleOrder")
    ResultObject sendDisassembleOrder(@RequestParam String orderId);
}
