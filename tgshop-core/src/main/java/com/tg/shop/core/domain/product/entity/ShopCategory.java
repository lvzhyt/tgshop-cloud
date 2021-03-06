package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopCategory implements Serializable {
    /**
     *  店铺类目id
     */
    private String shopCategoryId;

    /**
     * 类目id
     */
    private String categoryId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 卖家id
     */
    private String sellerId;

    /**
     * 排序
     */
    private Integer sortNum;

    /**
     * 状态：1为申请，2为通过，3为驳回，0 卖家删除
     */
    private Integer status;

    /**
     * 审核人
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