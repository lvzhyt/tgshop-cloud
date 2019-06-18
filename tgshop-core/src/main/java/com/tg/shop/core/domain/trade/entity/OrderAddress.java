package com.tg.shop.core.domain.trade.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class OrderAddress implements Serializable {
    /**
     * 订单收货地址id
     */
    private String orderAddressId;

    /**
     * 收货地址别名
     */
    private String aliseName;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 收货人
     */
    private String reciverName;

    /**
     * 收货电话
     */
    private String mobile;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 县市区
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否默认 0 否 1 是
     */
    private Integer useDefault;

    /**
     * 删除状态
     */
    private Integer isDel;

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