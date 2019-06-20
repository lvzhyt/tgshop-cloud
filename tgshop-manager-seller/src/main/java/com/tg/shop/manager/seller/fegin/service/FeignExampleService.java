package com.tg.shop.manager.seller.fegin.service;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.manager.seller.hystrix.FeignServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-producer",fallback = FeignServiceHystrix.class)
public interface FeignExampleService {
    @GetMapping("/goods/{goodsId}")
    Goods getGoods(@PathVariable String goodsId);
}
