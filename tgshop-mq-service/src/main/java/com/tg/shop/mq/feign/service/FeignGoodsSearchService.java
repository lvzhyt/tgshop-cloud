package com.tg.shop.mq.feign.service;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.hystrix.FeignGoodsSearchServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-search-service",fallback = FeignGoodsSearchServiceHystrix.class)
public interface FeignGoodsSearchService {

    @GetMapping("/es/updateGoodsSearchIndex")
    ResultObject updateGoodsSearchIndex(String goodsId);

}
