package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsAttributeValue;
import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.entity.ResultObject;

import java.util.List;

public interface GoodsAttributeValueService {

    int saveGoodsAttributeValue(GoodsAttributeValue record);

    GoodsAttributeValue getGoodsAttributeValueById(String tid);

    int batchUpdateGoodsAttribute(List<GoodsAttributeValue> list);

    List<GoodsAttributeValue> findGoodsAttributeValueList(GoodsAttributeValue goodsAttribute);

    /**
     * 唯一查询
     * @param goodsId
     * @param attrId
     * @param valueId
     * @return
     */
    GoodsAttributeValue findGoodsAttrValUnique(String goodsId, String attrId, String valueId);

    /**
     * 删除商品属性
     * @param goodsAttributeValue
     * @param goodsSkuList
     * @return
     */
    ResultObject deleteSpecAttrValue(GoodsAttributeValue goodsAttributeValue, List<GoodsSku> goodsSkuList);
}
