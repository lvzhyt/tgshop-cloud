package com.tg.shop.core.domain.product.vo;

import lombok.Data;

@Data
public class ShopAttributeValueSimpleVo {

    /**
     * 店铺属性值id
     */
    private String attrValueId;


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

}
