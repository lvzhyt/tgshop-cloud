package com.tg.shop.trade.request.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
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
     * 防重复提交
     */
    private String ticket;

    /**
     * 商品总金额
     */
    private BigDecimal goodsAmount;

    /**
     * 运费
     */
    private BigDecimal freightCharge;

    /**
     * 购物车 购买商品
     */
    @NotEmpty
    @ApiModelProperty(required = true)
    private List<String> cartIds;

    /**
     * 收货方式 1 邮寄 2 自提
     */
    @ApiModelProperty(example = "1")
    private Integer receiveType=1;

    /**
     * 收货地址id
     */
    private String receiveAddressId;

    /**
     * 自提地址
     */
    private String takeAddressId;

    /**
     * 配送时间范围
     */
    private String receiveTimeRange;

    /**
     * 自提时间范围
     */
    private String takeTimeRange;

    /**
     * 0 在线付款 1 货到付款
     */
    private Integer paymentType = 0;

    /**
     * 优惠券id
     */
    private String promotionId;

    /**
     * 优惠金额
     */
    private BigDecimal promotionDiscount;

    /**
     * 积分数量
     */
    private Integer integralNum;

    /**
     * 积分优惠
     */
    private BigDecimal integralDiscount;


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
     * 订单备注
     */
    private String orderRemark;


}
