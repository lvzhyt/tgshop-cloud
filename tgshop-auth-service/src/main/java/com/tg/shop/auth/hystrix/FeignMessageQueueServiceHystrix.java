package com.tg.shop.auth.hystrix;

import com.tg.shop.auth.feign.service.FeignMessageQueueService;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class FeignMessageQueueServiceHystrix implements FeignMessageQueueService {

    @Override
    public ResultObject sendHelloMq(String message) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }

    @Override
    public ResultObject goodsElasticSearch(String goodsId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }
}
