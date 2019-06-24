package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ShopAttributeValue implements Serializable {
    /**
     * 店铺属性值id
     */
    private String attrValueId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 属性id  表 item_attribute
     */
    private String attributeId;

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 排序
     */
    private Integer sortNum;

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