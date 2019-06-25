package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttribute;

import java.util.List;

public interface GoodsSkuAttributeService {

    int saveGoodsSkuAttribute(GoodsSkuAttribute record);

    GoodsSkuAttribute getGoodsSkuAttributeById(String tid);

    GoodsSkuAttribute findBySkuIdAttrName(String skuId, String attrName);

    List<GoodsSkuAttribute> findGoodsSkuAttributeList(GoodsSkuAttribute record);
}
