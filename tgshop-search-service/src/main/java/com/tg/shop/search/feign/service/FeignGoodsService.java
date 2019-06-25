package com.tg.shop.search.feign.service;

import com.github.pagehelper.PageInfo;
import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.hystrix.FeignGoodsServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-product-service",fallback = FeignGoodsServiceHystrix.class)
public interface FeignGoodsService {

    @RequestMapping("/goods/getGoodsById")
    ResultObject<Goods> getGoodsById(String goodsId);

    @PostMapping("/goods/findGoodsPageList")
    ResultObject findGoodsPageList(PageCondition<Goods> pageCondition);
}
