package com.tg.shop.core.domain.trade.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品确认商品详情
 * @author Administrator
 */
@Data
public class OrderConfirmDetailVo {

    private List<OrderStoreConfirmDetailVo> storeOrderItemList;

    /**
     * 商品金额
     */
    private BigDecimal goodsAmount;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 通用优惠券
     */
    private Object generalCoupon;

    /**
     * 订单优惠金额
     * 商品直降/立减优惠、 优惠券优惠(店铺优惠、通用优惠券不能同时使用)
     */
    private BigDecimal orderDiscountAmount;


}
