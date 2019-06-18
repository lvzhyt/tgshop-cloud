package com.tg.shop.core.domain.auth.form;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tg
 * @Date: 2019/3/18 19:29
 */
@Data
public class StoreForm implements Serializable {

    /**
     * 店铺名称
     */
    private String storeName;

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


}
