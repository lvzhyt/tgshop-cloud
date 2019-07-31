package com.tg.shop.mq.feign.service;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.hystrix.FeignGoodsSearchServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-trade-service",fallback = FeignGoodsSearchServiceHystrix.class)
public interface FeignTradeService {

    /**
     * 确认订单扣减库存
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/feign/trade/confirmOrderStock",method = RequestMethod.GET)
    ResultObject confirmOrderStock(@RequestParam(value = "orderId") String orderId);


    /**
     * 取消订单恢复库存
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/feign/trade/cancelOrderStock",method = RequestMethod.GET)
    ResultObject cancelOrderStock(@RequestParam(value = "orderId") String orderId);


    /**
     * 确认订单失败，取消确认订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/feign/trade/cancelConfirmFailedOrder",method = RequestMethod.GET)
    ResultObject cancelConfirmFailedOrder(@RequestParam(value = "orderId") String orderId);

    @RequestMapping(value = "/feign/trade/disassembleOrder",method = RequestMethod.GET)
    ResultObject disassembleOrder(@RequestParam(value = "orderId") String orderId);

}
