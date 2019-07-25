package com.tg.shop.trade.controller;

import com.tg.shop.core.domain.trade.entity.Cart;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.request.param.OrderParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Administrator
 */
@Api("订单交易")
@RestController
@RequestMapping("/trade")
public class TradeController {

    @ApiOperation("确认订单")
    @PostMapping("/confirmOrder")
    public ResultObject confirmOrder(@RequestBody List<Cart> cartList){
        System.out.println(cartList);

        return new ResultObject();
    }

    @ApiOperation("提交订单")
    @PostMapping("/saveOrder")
    public ResultObject saveOrder(@RequestBody @Valid OrderParam orderParam, BindingResult bindingResult){


        return new ResultObject();
    }


}
