package com.tg.shop.search.fegin.service;

import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;

import java.util.List;

public interface FeginGoodsSkuService {

    List<GoodsSkuDetailResultVo> findSkuDetailListByGoodsId(String goodsId);
}
