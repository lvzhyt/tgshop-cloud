package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopCategoryAttributeValue implements Serializable {
    /**
     * id
     */
    private String shopCategoryValueId;

    /**
     * 属性ID
     */
    private String attributeId;

    /**
     * 类目ID
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
     * 属性值ID
     */
    private String valueId;

    /**
     * 排序号
     */
    private Integer sortNumber;

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