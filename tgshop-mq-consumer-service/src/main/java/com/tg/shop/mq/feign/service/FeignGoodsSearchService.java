package com.tg.shop.mq.feign.service;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.hystrix.FeignGoodsSearchServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-search-service",fallback = FeignGoodsSearchServiceHystrix.class)
public interface FeignGoodsSearchService {

    /**
     * 更新商品索引
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/es/updateGoodsSearchIndex",method = RequestMethod.GET)
    ResultObject updateGoodsSearchIndex(@RequestParam(value = "goodsId") String goodsId);

}
