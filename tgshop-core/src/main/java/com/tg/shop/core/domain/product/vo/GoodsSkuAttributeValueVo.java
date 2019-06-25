package com.tg.shop.core.domain.product.vo;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttributeValue;
import lombok.Data;

@Data
public class GoodsSkuAttributeValueVo extends GoodsSkuAttributeValue {

    private String attributeName;

    private String attributeValueName;
}
