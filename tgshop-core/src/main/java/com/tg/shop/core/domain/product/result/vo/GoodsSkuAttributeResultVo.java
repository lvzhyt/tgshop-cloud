package com.tg.shop.core.domain.product.result.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsSkuAttributeResultVo implements Serializable {

    /**
     * 属性ID
     */
    private String attrId;

    /**
     * 属性值ID
     */
    private String valueId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值名称
     */
    private String attrValue;
}
