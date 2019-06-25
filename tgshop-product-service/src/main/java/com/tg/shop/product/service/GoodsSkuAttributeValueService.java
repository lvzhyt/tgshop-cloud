package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttributeValue;

import java.util.List;

public interface GoodsSkuAttributeValueService {

    int saveGoodsSkuAttributeValue(GoodsSkuAttributeValue record);

    GoodsSkuAttributeValue getGoodsSkuAttributeValueById(String tid);

    GoodsSkuAttributeValue findBySkuIdAttrName(String skuId, String attrId, String attrValue);

    List<GoodsSkuAttributeValue> findGoodsSkuAttributeValueList(GoodsSkuAttributeValue record);

}
