package com.tg.shop.trade.service;

import com.tg.shop.core.domain.trade.entity.TradeSkuInventory;

public interface TradeSkuInventoryService {
    TradeSkuInventory getBySkuId(String skuId);
    int updateSkuInventoryByVersion(TradeSkuInventory tradeSkuInventory);
}
