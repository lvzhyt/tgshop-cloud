package com.tg.shop.product.dao;

import com.tg.shop.core.domain.product.entity.GoodsSku;

import java.util.List;

public interface GoodsSkuDao {


    int deleteSku(GoodsSku goodsSku);

    int updateSkuById(GoodsSku goodsSku);

    List<GoodsSku> findSkuByCondition(GoodsSku condition);

    int saveSku(GoodsSku goodsSku);
}
