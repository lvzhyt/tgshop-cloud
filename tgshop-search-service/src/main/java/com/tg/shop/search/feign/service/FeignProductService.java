package com.tg.shop.search.feign.service;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.hystrix.FeignProductServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-product-service",fallback = FeignProductServiceHystrix.class)
public interface FeignProductService {

    /**
     * 获取商品
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/goods/getGoodsById",method = RequestMethod.GET)
    ResultObject<Goods> getGoodsById(@RequestParam("goodsId") String goodsId);

    @RequestMapping(value = "/goods/findGoodsPageList",method=RequestMethod.POST)
    ResultObject findGoodsPageList(@RequestBody PageCondition<Goods> pageCondition);

    /**
     * 获取商品sku详情列表
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/sku/getSkuDetailListByGoodsId",method = RequestMethod.GET)
    ResultObject<List<GoodsSkuDetailResultVo>> getSkuDetailListByGoodsId(@RequestParam("goodsId") String goodsId);
}
