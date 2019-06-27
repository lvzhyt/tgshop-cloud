package com.tg.shop.search.hystrix;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.search.feign.service.FeignGoodsService;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class FeignGoodsServiceHystrix implements FeignGoodsService {
    @Override
    public ResultObject getGoodsById(String goodsId) {
        return new ResultObject(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }

    @Override
    public ResultObject findGoodsPageList(PageCondition<Goods> pageCondition) {
        return new ResultObject(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }
}
