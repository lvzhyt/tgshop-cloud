package com.tg.shop.trade.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.annotation.UserToken;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.domain.base.BaseEntityInfo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.trade.entity.*;
import com.tg.shop.core.domain.trade.vo.CartDetailVo;
import com.tg.shop.core.domain.trade.vo.OrderInfo;
import com.tg.shop.core.domain.trade.vo.StoreSimpleVo;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.trade.domain.TradeDictionary;
import com.tg.shop.trade.exception.TradeException;
import com.tg.shop.trade.feign.service.FeignMessageQueueService;
import com.tg.shop.trade.feign.service.FeignProductService;
import com.tg.shop.trade.request.param.AddressParam;
import com.tg.shop.trade.request.param.ConfirmOrderParam;
import com.tg.shop.trade.request.param.OrderParam;
import com.tg.shop.trade.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
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
@Api("订单交易")
@RestController
@RequestMapping("/trade/order")
public class TradeOrderController {


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
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private FeignMessageQueueService feignMessageQueueService;

    /**
     * 订单确认页面
     * 购物车结算、商品页面直接购买结算--》订单确认页面
     * 填写订单信息：地址、支付方式、发票、卡券、积分、选择优惠券、计算运费
     * @return
     */
    @ApiOperation("确认订单")
    @PostMapping("/confirmOrder")
    public ResultObject confirmOrder(@Valid @RequestBody ConfirmOrderParam confirmOrderParam) throws TradeException {
        System.out.println(confirmOrderParam);

        List<Cart> cartList = cartService.findCartByIds(confirmOrderParam.getCartIds());
        JSONObject data = new JSONObject();

        Member member = CacheMemberHolderLocal.getMember();
        // 收货地址
        UserReceiveAddress address = null;
        if(StringUtils.isNotEmpty(confirmOrderParam.getReceiveAddressId())){
            address = userReceiveAddressService.getAddressById(confirmOrderParam.getReceiveAddressId());
        }else {
            UserReceiveAddress condition = new UserReceiveAddress();
            condition.setMemberId(member.getMemberId());
            address = userReceiveAddressService.getMemberDefaultAddress(member.getMemberId());
        }
        data.put("receiveAddress",address);

        // 自提地址
        if(StringUtils.isNotEmpty(confirmOrderParam.getTakeAddressId())){
            data.put("takeAddress","");
        }


        List<CartDetailVo> cartDetailVoList = getSkuDetailList(cartList);
        Map<String,List<CartDetailVo>> storeCartMap = new HashMap<>(16);
        List<StoreSimpleVo> storeSimpleVoList = new ArrayList<>();
        for (CartDetailVo vo :
                cartDetailVoList) {
            String key = vo.getStoreId();
            if (storeCartMap.containsKey(key)) {
                storeCartMap.get(key).add(vo);
            } else {
                List<CartDetailVo> list = new ArrayList<>();
                list.add(vo);
                storeCartMap.put(key, list);
                StoreSimpleVo storeSimpleVo = new StoreSimpleVo(key,vo.getStoreName());
                storeSimpleVoList.add(storeSimpleVo);
            }
        }
        data.put("storeSimpleVoList",storeSimpleVoList);
        data.put("storeCartMap",storeCartMap);

        // 可用优惠券
        JSONObject coupon = new JSONObject();
        List couponList = new ArrayList();
        data.put("usedCoupon",coupon);
        data.put("couponList",couponList);

        // 计算运费
        int payType = confirmOrderParam.getPaymentType();
        int shipFee = this.calculateShipFee(storeCartMap,payType,member);
        data.put("shipFee",shipFee);
        // 防止重复提交
        data.put("ticket",idGenerator.uuid());

        data.put("confirmOrderParam",confirmOrderParam);

        return new ResultObject<>(data);
    }

    /**
     * 提交订单
     *  地址、 商品 skuIds  goodsNum、支付方式、优惠券、积分、卡券
     *  ==>> 计算得出总金额
     * @param orderParam
     * @param bindingResult
     * @return
     */
    @ApiOperation("提交订单")
    @PostMapping("/saveOrder")
    public ResultObject saveOrder(@Valid @RequestBody OrderParam orderParam, BindingResult bindingResult) throws TradeException {
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        String tick = orderParam.getTicket();
        try{
            if(StringUtils.isNotEmpty(tick)){
                stringRedisTemplate.opsForValue().set(tick,tick,30, TimeUnit.SECONDS);
            }
            Member member = CacheMemberHolderLocal.getMember();
            List<Cart> cartList = cartService.findCartByIds(orderParam.getCartIds());
            List<CartDetailVo> cartDetailVoList = getSkuDetailList(cartList);
            if(cartDetailVoList==null|| cartDetailVoList.isEmpty()){
                return new ResultObject(ErrorCode.EMPTY_DATA_ERROR);
            }

            // 检查可用库存
            for (CartDetailVo vo :
                    cartDetailVoList) {
                if (vo.getGoodsNum() > vo.getLeftNum()){
                    String msg = "库存不足.SkuNo:"+vo.getSkuNo();
                    throw new TradeException(msg);
                }
            }
            
            Map<String,List<CartDetailVo>> storeCartMap = new HashMap<>(16);
            List<StoreSimpleVo> storeSimpleVoList = new ArrayList<>();
            for (CartDetailVo vo :
                    cartDetailVoList) {
                String key = vo.getStoreId();
                if (storeCartMap.containsKey(key)) {
                    storeCartMap.get(key).add(vo);
                } else {
                    List<CartDetailVo> list = new ArrayList<>();
                    list.add(vo);
                    storeCartMap.put(key, list);
                    StoreSimpleVo storeSimpleVo = new StoreSimpleVo(key,vo.getStoreName());
                    storeSimpleVoList.add(storeSimpleVo);
                }
            }

            Order order = new Order();
            String orderId = idGenerator.nextStringId();
            String orderSn = idGenerator.nextOrderSn();
            order.setOrderId(orderId);
            order.setOrderSn(orderSn);

            int paymentType = orderParam.getPaymentType();
            BigDecimal goodsAmount = this.calculateGoodsAmount(cartDetailVoList,member);
            BigDecimal freightCharge = new BigDecimal(String.valueOf(this.calculateShipFee(storeCartMap,paymentType,member)));

            order.setGoodsAmount(goodsAmount);
            order.setFreightCharge(freightCharge);

            BigDecimal promotionDiscount = new BigDecimal("0");
            String promotionId = orderParam.getPromotionId();
            if(StringUtils.isNotEmpty(promotionId)) {
                order.setPromotionId(promotionId);
                //TODO 获取优惠券,校验是否可用 获取优惠金额
                promotionDiscount = new BigDecimal("0");
            }
            order.setPromotionDiscount(promotionDiscount);
            BigDecimal integralDiscount = new BigDecimal("0");
            int integralNum = 0;
            if(orderParam.getIntegralNum()!=null&&orderParam.getIntegralNum()>=1000){
                integralNum = orderParam.getIntegralNum();
                integralDiscount = new BigDecimal(String.valueOf(orderParam.getIntegralNum()/100));
            }
            order.setIntegralNum(integralNum);
            order.setIntegralDiscount(integralDiscount);
            int receiveType = orderParam.getReceiveType();
            order.setReceiveType(receiveType);
            if(receiveType==1){
                Assert.notNull(orderParam.getReceiveAddressId(),"receiveType=1 receiveAddress must not null");
                UserReceiveAddress address = userReceiveAddressService.getAddressById(orderParam.getReceiveAddressId());
                order.setReceiveAddressId(orderParam.getReceiveAddressId());
                order.setAddressReceiverName(address.getReceiverName());
                order.setAddressReceiverMobile(address.getMobile());
                order.setAddressProvince(address.getProvince());
                order.setAddressCity(address.getCity());
                order.setAddressCounty(address.getCounty());
                order.setAddressDetailAddress(address.getDetailAddress());
            }else if(receiveType==2){
                //TODO 自提地址
                order.setTakeAddressId(orderParam.getTakeAddressId());
                order.setTakeTimeRange(orderParam.getTakeTimeRange());
            }
            order.setReceiveTimeRange(orderParam.getReceiveTimeRange());
            order.setTakeTimeRange(orderParam.getTakeTimeRange());
            //
            String storeId= TradeDictionary.MULTI_STORE_ID;
            if(storeSimpleVoList.size()==1){
                storeId = storeSimpleVoList.get(0).getStoreId();
            }
            order.setInvoiceNeed(orderParam.getInvoiceNeed());
            if(orderParam.getInvoiceNeed()!=null && order.getInvoiceNeed()==1){
                Assert.notNull(orderParam.getInvoiceTitle(),"invoiceNeed=1 InvoiceTitle must be not null");
                Assert.notNull(orderParam.getInvoiceCorporationTax(),"invoiceNeed=1 InvoiceCorporationTax must be not null");
                order.setInvoiceTitle(orderParam.getInvoiceTitle());
                order.setInvoiceCorporationTax(orderParam.getInvoiceCorporationTax());
            }
            order.setOrderRemark(orderParam.getOrderRemark());

            order.setBuyerId(member.getMemberId());
            order.setBuyerName(member.getNickName());
            order.setBuyerMobile(member.getPhone());
            order.setSellerId("-1");
            order.setStoreId(storeId);

            order.setPromotionId(orderParam.getPromotionId());
            order.setAdjustAmount(new BigDecimal("0"));
            // totalDiscount = 优惠券+调价
            BigDecimal totalDiscount = order.getPromotionDiscount().add(order.getAdjustAmount());
            order.setTotalDiscount(totalDiscount);
            BigDecimal totalPrice = goodsAmount.add(freightCharge).subtract(totalDiscount);
            order.setTotalPrice(totalPrice);
            BigDecimal paymentPrice = totalPrice.subtract(integralDiscount);
            order.setPaymentPrice(paymentPrice);
            order.setOrderState(OrderInfo.ORDER_STATE_WAIT_CONFIRM);
            order.setCreator(member.getMemberId());
            Date createTime = new Date();
            order.setCreateTime(createTime);

            BigDecimal sumItemPayPrice = new BigDecimal("0");
            int count = 0;
            List<OrderItem> orderItemsList = new ArrayList<>();
            for (CartDetailVo vo:cartDetailVoList){
                count++;
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemId(idGenerator.nextStringId());
                orderItem.setOrderId(orderId);
                orderItem.setGoodsId(vo.getGoodsId());
                orderItem.setGoodsSn(vo.getGoodsSn());
                orderItem.setGoodsName(vo.getGoodsName());
                orderItem.setSkuId(vo.getSkuId());
                orderItem.setSkuNo(vo.getSkuNo());
                orderItem.setSkuGoodsName(vo.getSkuGoodsName());
                orderItem.setSpecInfo(vo.getSpecInfo());
                orderItem.setCategoryId(vo.getCategoryId());
                orderItem.setCategoryName(vo.getCategoryName());
                orderItem.setBrandId(vo.getBrandId());
                orderItem.setBrandName(vo.getBrandName());
                orderItem.setGoodsNum(vo.getGoodsNum());
                orderItem.setGoodsPrice(vo.getGoodsPrice());
                BigDecimal goodsPriceTotal = vo.getGoodsPrice().multiply(new BigDecimal(vo.getGoodsNum()));
                orderItem.setGoodsPriceTotal(goodsPriceTotal);
                orderItem.setShopFreightTemplateId(vo.getTemplateId());
                orderItem.setCreator(member.getMemberId());
                orderItem.setCreateTime(createTime);
                BigDecimal payPriceTotal;
                // 最后一个 用总的减去上面其他的和，即均摊剩余值
                if(count==cartDetailVoList.size()){
                    payPriceTotal = paymentPrice.subtract(sumItemPayPrice);
                }else{
                    payPriceTotal = paymentPrice.multiply(goodsPriceTotal).divide(goodsAmount,2,BigDecimal.ROUND_HALF_UP);
                    sumItemPayPrice = sumItemPayPrice.add(payPriceTotal);
                }
                orderItem.setPayPriceTotal(payPriceTotal);
                BigDecimal payPrice = payPriceTotal.divide(new BigDecimal(String.valueOf(orderItem.getGoodsNum())),2,BigDecimal.ROUND_HALF_UP);
                orderItem.setPayPrice(payPrice);
                orderItemsList.add(orderItem);
            }
            OrderLog orderLog = new OrderLog();
            orderLog.setOrderLogId(idGenerator.nextStringId());
            orderLog.setOrderId(order.getOrderId());
            orderLog.setOrderSn(order.getOrderSn());
            orderLog.setOrderState(String.valueOf(order.getOrderState()));
            String remark = "提交订单";
            orderLog.setRemark(remark);
            orderLog.setOperator(member.getNickName());
            orderLog.setCreator(order.getCreator());
            orderLog.setCreateTime(new Date());
            // 保存订单 发送确认消息
            orderService.saveOrder(order,orderItemsList,orderLog);
            stringRedisTemplate.opsForValue().set(orderId,orderId,15,TimeUnit.SECONDS);
        }catch (Exception e){
            throw e;
        }finally {
            if(StringUtils.isNotEmpty(tick)){
                stringRedisTemplate.delete(tick);
            }
        }

        return new ResultObject();
    }

    @ApiOperation("订单列表")
    public ResultObject listOrderByPage(@RequestParam(value = "orderState",required = false) Integer orderState,
                                  @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                                  @RequestParam(value = "pageNo",required = false,defaultValue = "1") Integer pageNo){
        Member member = CacheMemberHolderLocal.getMember();
        Order condition = new Order();
        condition.setOrderState(orderState);
        condition.setBuyerId(member.getMemberId());

        orderService.findOrderPageList(condition,pageSize,pageNo);

        return null;
    }

    /**
     * 获取收货地址
     * @return
     */
    @ApiOperation("获取收货地址")
    @GetMapping("/getReceiveAddressList")
    public ResultObject<List<UserReceiveAddress>> getReceiveAddressList(){
        Member member = CacheMemberHolderLocal.getMember();
        UserReceiveAddress condition = new UserReceiveAddress();
        condition.setMemberId(member.getMemberId());
        List<UserReceiveAddress> list = userReceiveAddressService.findAddressByCondition(condition);
        return new ResultObject<>(list);
    }

    /**
     * 保存收货地址
     * @param addressParam
     * @param bindingResult
     * @return
     */
    @ApiOperation("保存收货地址")
    @PostMapping("/saveReceiveAddress")
    public ResultObject saveReceiveAddress(@Valid @RequestBody AddressParam addressParam,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResultObject<>(ErrorCode.REQUEST_PARAM_ERROR,bindingResult.getFieldErrors());
        }
        Member member = CacheMemberHolderLocal.getMember();

        UserReceiveAddress userReceiveAddress = new UserReceiveAddress();
        BeanUtils.copyProperties(addressParam,userReceiveAddress);
        userReceiveAddress.setAddressId(idGenerator.nextStringId());
        userReceiveAddress.setMemberId(member.getMemberId());
        userReceiveAddress.setCreateTime(new Date());
        userReceiveAddress.setCreator(member.getMemberId());

        int result = userReceiveAddressService.saveAddress(userReceiveAddress);
        if(addressParam.getUseDefault()==1){
            // 取消其他默认地址 设置默认地址
            userReceiveAddressService.setDefaultReceiveAddress(userReceiveAddress.getAddressId());
        }
        return new ResultObject<>(userReceiveAddress);
    }

    /**
     * 设置默认收货地址
     * @param addressId
     * @return
     */
    @ApiOperation("设置默认收货地址")
    @PostMapping("/setDefaultReceiveAddress")
    public ResultObject setDefaultReceiveAddress(@RequestParam String addressId){
        // 取消其他默认地址 设置默认地址
        boolean success = userReceiveAddressService.setDefaultReceiveAddress(addressId);
        return new ResultObject<>(success);
    }


    /**
     * 在线支付
     * 返回参数 调起支付宝、微信
     * 在支付宝、微信回调页面完成付款确认
     * @param orderId
     * @return
     */
    @ApiOperation("订单在线支付")
    @PostMapping("/payOrderOnLine")
    public ResultObject payOrderOnLine(String orderId){

        return new ResultObject();
    }

    /**
     * 用户取消订单
     * 恢复锁定库存
     * @param orderId
     * @return
     */
    @ApiOperation("用户取消订单")
    @PostMapping("/member/cancelOrder")
    public ResultObject memberCancelOrder(String orderId){
        Member member = CacheMemberHolderLocal.getMember();
        Order order = orderService.getByOrderId(orderId);
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderLogId(idGenerator.nextStringId());
        orderLog.setOrderId(orderId);
        orderLog.setOrderSn(order.getOrderSn());
        orderLog.setOrderState(order.getOrderState()+"");
        String remark = "取消订单";
        orderLog.setRemark(remark);
        orderLog.setOperator(member.getNickName());
        orderLog.setCreator(member.getMemberId());
        orderLog.setCreateTime(new Date());
        return orderService.cancelOrder(order,orderLog);
    }

    /**
     * 确认订单
     * 锁定库存
     * @param orderId
     * @return
     */
    @ApiOperation("确认订单")
    @PostMapping("/confirmOrder")
    public ResultObject confirmOrder(String orderId){
        Order order = orderService.getByOrderId(orderId);
        if(order.getOrderState()!=OrderInfo.ORDER_STATE_WAIT_CONFIRM){
            return new ResultObject();
        }
        ResultObject resultObject = orderService.confirmOrder(orderId);

        return resultObject;
    }


    /**
     * 获取订单是否下单成功
     * @param orderId
     * @return  1 等待 2 成功
     */
    @ApiOperation("获取订单是否下单成功")
    @GetMapping("/getSubmitOrder")
    public ResultObject getSubmitOrder(String orderId){
        Order order = orderService.getByOrderId(orderId);
        // 待支付状态
        boolean success = order.getOrderState() == OrderInfo.ORDER_STATE_WAIT_PAY;
        if(success){
            return new ResultObject<>(2);
        }
        if(!stringRedisTemplate.hasKey(orderId)){
            order.setOrderState(0);
            order.setIsDel(BaseEntityInfo.STATE_DELETE);
            order.setModifier("sys getSubmitOrder");
            order.setModifyTime(new Date());

            OrderLog orderLog = new OrderLog();
            orderLog.setOrderLogId(idGenerator.nextStringId());
            orderLog.setOrderId(order.getOrderId());
            orderLog.setOrderSn(order.getOrderSn());
            orderLog.setOrderState(String.valueOf(order.getOrderState()));
            String remark = "确认订单超时，删除订单";
            orderLog.setRemark(remark);
            orderLog.setOperator("sys getSubmitOrder");
            orderLog.setCreator(order.getCreator());
            orderLog.setCreateTime(new Date());
            int count = orderService.updateOrder(order,orderLog);
            return new ResultObject<>(ErrorCode.TRADE_CONFIRM_ORDER_OUT_OF_TIME_ERROR);
        }
        // 等待
        ResultObject resultObject = new ResultObject<>(1);
        return resultObject;
    }

    private List<CartDetailVo> getSkuDetailList(List<Cart> cartList) throws TradeException {
        List<CartDetailVo> result = new ArrayList<>();
        for (Cart cart:cartList) {
            ResultObject<GoodsSkuDetailResultVo>  skuDetailResultVoResultObject = feignProductService.getSkuDetailBySkuId(cart.getSkuId());
            if(skuDetailResultVoResultObject.getResult()==0){
                throw new TradeException(skuDetailResultVoResultObject.getMessage());
            }
            CartDetailVo cartDetailVo = new CartDetailVo();

            GoodsSkuDetailResultVo skuDetail = skuDetailResultVoResultObject.getData();
            // 检查可用库存
            if (cartDetailVo.getGoodsNum() > skuDetail.getLeftNum()){
                String msg = "库存不足.SkuNo:"+cartDetailVo.getSkuNo();
                throw new TradeException(msg);
            }
            BeanUtils.copyProperties(skuDetail,cartDetailVo);
            BeanUtils.copyProperties(cart,cartDetailVo);
            result.add(cartDetailVo);
        }
        return  result;
    }

    /**
     * 计算运费
     * member 根据会员等级、plus 判断包邮
     * 同一店铺：计算最低邮费
     * 最终邮费：所有店铺邮费的和
     * 最低邮费：一个包邮即所有包邮；满额包邮：店铺最低满额值；支付免邮：订单支付方式；不包邮：最低邮费不累加
     * @return
     */
    private int calculateShipFee(Map<String,List<CartDetailVo>> storeCartMap,int payType, Member member){
        int shipFee = 0;
        Iterator<String> it = storeCartMap.keySet().iterator();
        while(it.hasNext()){
            List<CartDetailVo> list = storeCartMap.get(it.next());
            int storeShipFee = 0;
            BigDecimal goodsAmount = this.calculateGoodsAmount(list,member);
            for (CartDetailVo sku:list) {
                String templateId = sku.getTemplateId();
                FreightChargeTempTrade freightChargeTempTrade = freightChargeTempTradeService.getFreightChargeTemplateById(templateId);
                int type = freightChargeTempTrade.getTemplateType();
                if(type==1){
                    // 包邮返回运费0
                    storeShipFee = 0;
                    break;
                }else if(type==2){
                    // 满额包邮
                    if(goodsAmount.doubleValue()>freightChargeTempTrade.getFulfilPrice().doubleValue()){
                        // 店铺订单商品满足包邮，返回运费0
                        storeShipFee = 0;
                        break;
                    }else{
                        int fee = freightChargeTempTrade.getFreightPrice().intValue();
                        if(storeShipFee==0){
                            storeShipFee = fee;
                        }
                        // 取最小值
                        storeShipFee = storeShipFee<fee?storeShipFee:fee;
                    }
                }else if(type==3){
                    // 在线支付免邮
                    if(payType==0){
                        storeShipFee=0;
                        break;
                    }else{
                        storeShipFee = freightChargeTempTrade.getFreightPrice().intValue();
                    }
                }else if(type==4){
                    // 不包邮
                    int fee = freightChargeTempTrade.getFreightPrice().intValue();
                    if(storeShipFee==0){
                        storeShipFee = fee;
                    }
                    // 取最小值
                    storeShipFee = storeShipFee<fee?storeShipFee:fee;
                }

            }
            // 运费 = 店铺运费的和
            shipFee +=storeShipFee;
        }

        return shipFee;
    }

    private BigDecimal calculateGoodsAmount(List<CartDetailVo> skuList, Member member){
        // 未考虑促销活动
        BigDecimal goodsAmount = new BigDecimal("0");
        for (CartDetailVo sku:skuList) {
            BigDecimal price = sku.getSalePrice();
            if(sku.getPlusPriceOpen()==1 && member.getPlus()==1){
                price = sku.getPlusPrice();
            }
            // 单价*数量
            goodsAmount.add(price.multiply(new BigDecimal(sku.getGoodsNum())));
        }
        return goodsAmount;
    }



    /**
     * 计算优惠券
     */
    private Object caculateCoupon(List<CartDetailVo> skuList,Member member){
        return null;
    }




}

