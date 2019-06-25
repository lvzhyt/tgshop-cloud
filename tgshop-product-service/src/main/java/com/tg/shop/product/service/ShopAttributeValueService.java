package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.ShopAttributeValue;

import java.util.List;

public interface ShopAttributeValueService {

    ShopAttributeValue getShopAttributeValueById(String id);

    int saveShopAttributeValue(ShopAttributeValue record);

    int deleteShopAttributeValue(ShopAttributeValue record);

    List<ShopAttributeValue> findListByCondition(ShopAttributeValue condition);

    /**
     * 店铺+属性+属性值  唯一
     * @param storeId
     * @param attrId
     * @param attrValue
     * @return
     */
    ShopAttributeValue findShopAttrValUnique(String storeId, String attrId, String attrValue);
}
