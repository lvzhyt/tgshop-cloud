package com.tg.shop.trade.request.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

/**
 * 确认订单参数
 * 购物车商品、收货方式、地址、支付方式、优惠券、积分
 * @author Administrator
 */
@Data
@NoArgsConstructor
@ToString
public class ConfirmOrderParam {

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
    private Integer paymentType=0;

    /**
     * 优惠券id
     */
    private String promoteId;

    /**
     * 积分数量
     */
    private Integer integralNum;


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
