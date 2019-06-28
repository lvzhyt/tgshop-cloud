package com.tg.shop.product.service;

import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.util.PageCondition;

/**
 * @Author: tg
 * @Date: 2019/3/20 16:47
 */
public interface GoodsService {

    int saveGoods(Goods goods);

    int updateGoodsById(Goods goods);

    int deleteGoods(Goods goods);

    Goods getGoodsById(String goodsId);

    PageInfo<Goods> findGoodsPageList(PageCondition<Goods> pageCondition);

    Goods getGoodsBySn(String goodsSn);

    PageInfo<Goods> findGoodsWithSkuListPageList(PageCondition<Goods> pageCondition);

    int saveGoodsAndSku(Goods goods, GoodsSku sku);
}
