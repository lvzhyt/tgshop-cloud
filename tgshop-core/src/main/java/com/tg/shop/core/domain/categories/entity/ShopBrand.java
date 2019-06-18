package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopBrand implements Serializable {
    /**
     * 店铺品牌id
     */
    private String shopBrandId;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 三级类目id
     */
    private String categoryId;

    /**
     * 状态：1为申请，2为通过，3为驳回，0删除
     */
    private Integer status;

    /**
     * 审核人id
     */
    private String operatorId;

    /**
     * 备注
     */
    private String comment;

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