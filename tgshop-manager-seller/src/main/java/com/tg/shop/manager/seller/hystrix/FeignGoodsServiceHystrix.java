package com.tg.shop.manager.seller.hystrix;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.manager.seller.fegin.service.FeignGoodsService;

public class FeignGoodsServiceHystrix implements FeignGoodsService {

    @Override
    public ResultObject<Goods> getGoodsById(String goodsId) {
        return new ResultObject<>(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }

    @Override
    public ResultObject<Goods> getGoodsBySn(String goodsSn) {
        return new ResultObject<>(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }
}
