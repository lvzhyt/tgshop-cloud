package com.tg.shop.product.hystrix;

import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.product.feign.service.FeignMessageQueueControllerService;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class FeignMessageQueueControllerServiceHystrix implements FeignMessageQueueControllerService {

    @Override
    public ResultObject sendHelloMq(String message) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }

    @Override
    public ResultObject goodsElasticSearch(String goodsId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }
}
