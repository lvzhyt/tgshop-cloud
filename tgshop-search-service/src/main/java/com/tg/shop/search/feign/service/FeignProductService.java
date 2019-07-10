package com.tg.shop.search.feign.service;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.hystrix.FeignProductServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-product-service",fallback = FeignProductServiceHystrix.class)
public interface FeignProductService {

    @GetMapping("/goods/getGoodsById")
    ResultObject<Goods> getGoodsById(String goodsId);

    @PostMapping("/goods/findGoodsPageList")
    ResultObject findGoodsPageList(PageCondition<Goods> pageCondition);

    @GetMapping("/sku/findSkuDetailListByGoodsId")
    ResultObject<List<GoodsSkuDetailResultVo>> findSkuDetailListByGoodsId(String goodsId);
}
