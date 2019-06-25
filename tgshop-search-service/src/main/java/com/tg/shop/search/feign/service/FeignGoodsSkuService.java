package com.tg.shop.search.feign.service;

import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;

import java.util.List;

public interface FeignGoodsSkuService {

    List<GoodsSkuDetailResultVo> findSkuDetailListByGoodsId(String goodsId);
}
