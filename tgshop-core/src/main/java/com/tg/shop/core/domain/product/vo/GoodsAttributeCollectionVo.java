package com.tg.shop.core.domain.product.vo;

import com.tg.shop.core.domain.product.entity.GoodsAttribute;
import com.tg.shop.core.domain.product.entity.GoodsAttributeValue;
import lombok.Data;

import java.util.List;

@Data
public class GoodsAttributeCollectionVo extends GoodsAttribute {

    private List<GoodsAttributeValue> attributeValueList;
}
