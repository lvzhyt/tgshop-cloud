package com.tg.shop.trade.request.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单 收货地址
 * @author Administrator
 */
@Data
public class AddressParam {

    /**
     * 收货地址别名
     */
    private String aliasName;


    /**
     * 收货人
     */
    @NotEmpty
    private String receiverName;

    /**
     * 收货电话
     */
    @NotEmpty
    private String mobile;

    /**
     * 省份
     */
    @NotEmpty
    private String province;

    /**
     * 城市
     */
    @NotEmpty
    private String city;

    /**
     * 县市区
     */
    @NotEmpty
    private String county;

    /**
     * 详细地址
     */
    @NotEmpty
    private String detailAddress;

    /**
     * 是否默认 0 否 1 是
     */
    @ApiModelProperty(example = "0")
    private Integer useDefault = 0;

}
