package com.tg.shop.mq.hystrix;

import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.mq.feign.service.FeignGoodsSearchService;
import org.springframework.stereotype.Component;

@Component
public class FeignGoodsSearchServiceHystrix implements FeignGoodsSearchService {
    @Override
    public ResultObject updateGoodsSearchIndex(String goodsId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }
}
