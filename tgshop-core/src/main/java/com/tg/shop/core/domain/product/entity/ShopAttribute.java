package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopAttribute implements Serializable {
    /**
     * 商家属性id
     */
    private String shopAttributeId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 属性id
     */
    private String attributeId;

    /**
     * 排序
     */
    private Integer sortNum;

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