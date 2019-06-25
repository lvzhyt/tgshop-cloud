package com.tg.shop.product.service;

import com.tg.shop.core.domain.product.entity.GoodsSkuPrice;
import com.tg.shop.core.domain.product.entity.GoodsSkuPriceLog;

public interface GoodsSkuPriceService {

    int saveSkuPrice(GoodsSkuPrice record);

    GoodsSkuPrice getBySkuId(String skuId);

    int updateSkuPrice(GoodsSkuPrice record, GoodsSkuPriceLog priceLog);
}
