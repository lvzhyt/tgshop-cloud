package com.tg.shop.trade.controller;

import com.tg.shop.core.domain.trade.entity.Order;
import com.tg.shop.core.domain.trade.vo.OrderInfo;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign调用 没有Token
 * @author Administrator
 */
@RestController
public class FeignTradeOrderController {


    @Autowired
    private OrderService orderService;

    /**
     * feign 确认订单扣减库存
     * @param orderId
     * @return
     */
    @ApiOperation("feign 确认订单扣减库存")
    @RequestMapping(value = "/feign/trade/confirmOrderStock",method = RequestMethod.GET)
    public ResultObject confirmOrderStock(@RequestParam(value = "orderId") String orderId){
        Order order = orderService.getByOrderId(orderId);
        if(order.getOrderState()!= OrderInfo.ORDER_STATE_WAIT_CONFIRM){
            return new ResultObject();
        }
        ResultObject resultObject = orderService.confirmOrder(orderId);

        return resultObject;
    }


    /**
     * feign 恢复库存
     * 取消订单时恢复库存
     * @param orderId
     * @return
     */
    @ApiOperation("feign 取消订单恢复库存")
    @RequestMapping(value = "/feign/trade/cancelOrderStock",method = RequestMethod.GET)
    public ResultObject cancelOrderStock(@RequestParam(value = "orderId") String orderId){
        ResultObject resultObject = orderService.cancelOrderStock(orderId);
        return resultObject;
    }


    /**
     * feign 取消订单恢复库存
     * @param orderId
     * @return
     */
    @ApiOperation("feign 取消订单恢复库存")
    @RequestMapping(value = "/feign/trade/cancelConfirmFailedOrder",method = RequestMethod.GET)
    public ResultObject cancelConfirmFailedOrder(@RequestParam(value = "orderId") String orderId){
        return  orderService.cancelOrderStock(orderId);
    }


    /**
     * feign 拆单
     * @param orderId
     * @return
     */
    @ApiOperation("feign 拆单")
    @RequestMapping(value = "/feign/trade/disassembleOrder",method = RequestMethod.GET)
    public ResultObject disassembleOrder(@RequestParam(value = "orderId") String orderId){
        return orderService.disassembleOrder(orderId);
    }
}
