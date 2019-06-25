package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSkuInventory;

public interface GoodsSkuInventoryService {

    int saveSkuInventory(GoodsSkuInventory record);

    GoodsSkuInventory getBySkuId(String skuId);


    int updateSkuInventoryByVersion(GoodsSkuInventory inventory);
}
