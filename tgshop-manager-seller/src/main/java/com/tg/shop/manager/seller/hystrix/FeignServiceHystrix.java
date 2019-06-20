package com.tg.shop.manager.seller.hystrix;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.manager.seller.fegin.service.FeignExampleService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author SongChao
 * @version 1.0
 * @website https://github.com/Jaysong2012
 * @date 2018/11/8
 * @since 1.0
 */
@Component
public class FeignServiceHystrix implements FeignExampleService {


    @Override
    public Goods getGoods(@PathVariable String goodsId) {
        return new Goods();
    }
}
