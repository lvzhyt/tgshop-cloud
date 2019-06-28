package com.tg.shop.manager.seller.fegin.service;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.manager.seller.hystrix.FeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-product-service",fallback = FeignServiceHystrix.class)
public interface FeignGoodsService {

    @GetMapping("/goods/getGoodsById")
    ResultObject<Goods> getGoodsById(@RequestParam String goodsId);

    @GetMapping("/goods/getGoodsBySn")
    ResultObject<Goods> getGoodsBySn(@RequestParam String goodsSn);
}
