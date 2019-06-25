package com.tg.shop.search.fegin.service;

import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;

public interface FeginGoodsService {

    Goods getGoodsById(String goodsId);

    PageInfo<Goods> findGoodsPageList(PageCondition<Goods> pageCondition);
}
