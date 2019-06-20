package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Store implements Serializable {
    /**
     * 店铺ID
     */
    private String storeId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 电话
     */
    private String tel;

    /**
     * 联系人
     */
    private String linkManName;

    /**
     * 联系人电话
     */
    private String linkManTel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 店铺建新状态;1是申请，2是通过，3是驳回， 4是平台关闭，5是开通
     */
    private Integer shopStatus;

    /**
     * 店铺运行状态（只能允许卖家操作，默认为不开启），1表示卖家开启铺店，2表示卖家关闭店铺
     */
    private Integer runStatus;

    /**
     * 省份代码
     */
    private String provinceCode;

    /**
     * 省份名字
     */
    private String provinceName;

    /**
     * 市的代码
     */
    private String cityCode;

    /**
     * 市的名字
     */
    private String cityName;

    /**
     * 街道名字
     */
    private String streetName;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 维度
     */
    private Double latitude;

    /**
     * 店铺信息修改状态，1为待审核，2为驳回，3为修改通过
     */
    private Integer modifyStatus;

    /**
     * 删除状态 1 删除
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