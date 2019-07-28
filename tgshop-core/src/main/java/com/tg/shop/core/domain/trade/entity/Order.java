package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order implements Serializable {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 父订单id  0未拆单，1为父订单，其它为父订单ID
     */
    private String parentId;

    /**
     * 父订单编号
     */
    private String parentOrderSn;

    /**
     * 订单来源编码
     */
    private String originOrderSn;

    /**
     * 买家id
     */
    private String buyerId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerMobile;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 订单状态 1 待付款 2. 待发货 3 待收货  6 已完成 0 已取消 
     */
    private Integer orderState;

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
    private String promoteId;

    /**
     * 优惠金额
     */
    private BigDecimal promoteDiscount;

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
     * 调价时间
     */
    private Date changePaymentPriceTime;

    /**
     * 调价操作员
     */
    private String changePaymentPriceOperator;

    /**
     * 总优惠价
     */
    private BigDecimal totalDiscount;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 交易对外编号
     */
    private String tradeOutSn;

    /**
     * 交易流水号
     */
    private String tradeSn;

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
     * 支付类型 1 支付宝 2 微信 3 现金 4 银联
     */
    private Integer paymentKind;

    /**
     * 支付时间
     */
    private Date paymentTime;

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
     * 收货方式 1 邮寄 2 自提
     */
    private Integer receiveType;

    /**
     * 收货人
     */
    private String addressReceiverName;

    /**
     * 收货电话
     */
    private String addressReceiverMobile;

    /**
     * 邮编
     */
    private String addressZipCode;

    /**
     * 省份
     */
    private String addressProvince;

    /**
     * 城市
     */
    private String addressCity;

    /**
     * 县市区
     */
    private String addressCounty;

    /**
     * 详细收货地址
     */
    private String addressDetailAddress;

    /**
     * 物流公司
     */
    private String logisticsCompany;

    /**
     * 物流公司编号
     */
    private String logisticsCompanyCode;

    /**
     * 物流单号
     */
    private String logisticsNo;

    /**
     * 物流备注
     */
    private String logisticsRemark;

    /**
     * 订单备注
     */
    private String orderRemark;

    /**
     * 卖家备注
     */
    private String sellerRemark;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 发货时间
     */
    private Date deliverTime;

    /**
     * 确认收货时间
     */
    private Date confirmReceiptTime;

    /**
     * 订单完成时间
     */
    private Date orderFinishTime;

    /**
     * 买家评论 0 无 1 是
     */
    private Integer buyerEvaluate;

    /**
     * 卖家评论  0 无 1 是
     */
    private Integer sellerEvaluate;

    /**
     * 退款 0 无 1 是
     */
    private Integer refund;

    /**
     * 退款状态
     */
    private Integer refundState;

    /**
     * 删除状态
     */
    private Integer isDel;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}