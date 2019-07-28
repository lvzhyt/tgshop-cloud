package com.tg.shop.trade.request.param;

import com.tg.shop.core.domain.trade.entity.Cart;
import com.tg.shop.core.domain.trade.entity.UserReceiveAddress;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 保存订单参数
 * @author Administrator
 */
@Data
@NoArgsConstructor
@ToString
public class OrderParam {


    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 商品总金额
     */
    private BigDecimal goodsAmount;

    /**
     * 运费
     */
    private BigDecimal freightCharge;

    /**
     * 优惠券id
     */
    private String promoId;

    /**
     * 优惠金额
     */
    private BigDecimal promoDiscount;

    /**
     * 积分数量
     */
    private Integer integralNum;

    /**
     * 积分优惠金额
     */
    private BigDecimal integralDiscount;

    /**
     * 调价金额
     */
    private BigDecimal adjustAmount;

    /**
     * 总优惠价
     */
    private BigDecimal totalDiscount;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 实际支付金额
     */
    private BigDecimal paymentPrice;

    /**
     * 支付状态 0 未付款 1 已付款
     */
    private Integer paymentState;

    /**
     * 0 在线付款 1 货到付款
     */
    private Integer paymentType;


    /**
     * 是否有发票 0 否 1是
     */
    private Integer invoiceNeed;

    /**
     * 发票标题
     */
    private String invoiceTitle;

    /**
     * 发票税号
     */
    private String invoiceCorporationTax;

    /**
     * 收货地址id
     */
    private String addressId;

    /**
     * 订单备注
     */
    private String orderRemark;


    /**
     * 购物车 购买商品
     */
    private List<String> cartIds;


}
