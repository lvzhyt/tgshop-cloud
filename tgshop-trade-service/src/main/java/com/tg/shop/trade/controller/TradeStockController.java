package com.tg.shop.trade.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.annotation.UserToken;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.trade.entity.*;
import com.tg.shop.core.domain.trade.vo.CartDetailVo;
import com.tg.shop.core.domain.trade.vo.StoreSimpleVo;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.trade.exception.TradeException;
import com.tg.shop.trade.feign.service.FeignProductService;
import com.tg.shop.trade.request.param.AddressParam;
import com.tg.shop.trade.request.param.ConfirmOrderParam;
import com.tg.shop.trade.request.param.OrderParam;
import com.tg.shop.trade.service.CartService;
import com.tg.shop.trade.service.FreightChargeTempTradeService;
import com.tg.shop.trade.service.OrderService;
import com.tg.shop.trade.service.UserReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@UserToken
@Api("交易库存")
@RestController
@RequestMapping("/trade/stock")
public class TradeStockController {


    @Autowired
    private IdGenerator idGenerator;
    @Resource
    private FeignProductService feignProductService;
    @Autowired
    private UserReceiveAddressService userReceiveAddressService;
    @Autowired
    private FreightChargeTempTradeService freightChargeTempTradeService;
    @Autowired
    private CartService cartService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderService orderService;

    @ApiOperation("恢复订单库存")
    @GetMapping("/recoverOrderStock")
    public ResultObject recoverOrderStock(String orderId){

        return new ResultObject();
    }


}

