package com.tg.shop.trade.hystrix;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.util.PageCondition;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.feign.service.FeignProductService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 */
@Component
public class FeignProductServiceHystrix implements FeignProductService {
    @Override
    public ResultObject getGoodsById(String goodsId) {
        return new ResultObject(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }

    @Override
    public ResultObject findGoodsPageList(PageCondition<Goods> pageCondition) {
        return new ResultObject(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }

    @Override
    public ResultObject<List<GoodsSkuDetailResultVo>> getSkuDetailListByGoodsId(String goodsId) {
        return new ResultObject<>(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }

    @Override
    public ResultObject<GoodsSkuDetailResultVo> getSkuDetailBySkuId(String skuId) {
        return new ResultObject<>(ErrorCode.HYSTRIX_SERVICE_ERROR);
    }
}
