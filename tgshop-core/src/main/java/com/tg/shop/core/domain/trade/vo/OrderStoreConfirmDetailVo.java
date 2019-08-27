package com.tg.shop.core.domain.trade.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单确认店铺
 * @author Administrator
 */
@Data
public class OrderStoreConfirmDetailVo {

    private String storeId;

    private String storeName;

    /**
     * 店铺优惠价格
     */
    private BigDecimal storeDiscountAmount;

    /**
     * 店铺商品价格
     */
    private BigDecimal storeGoodsAmount;
    /**
     * 店铺订单价格
     */
    private BigDecimal storeOrderAmount;

    /**
     * 店铺商品
     */
    private List<OrderSkuItemConfirmDetailVo> skuItemList = new ArrayList<>();

    /**
     * 推荐优惠券
     */
    private Object recommendCoupon;

    /**
     * 可用优惠券列表
     */
    private List couponList;

    /**
     * 不可用优惠券列表
     */
    private List disableCouponList;




}
