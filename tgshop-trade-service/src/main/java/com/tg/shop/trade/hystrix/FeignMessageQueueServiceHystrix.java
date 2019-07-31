package com.tg.shop.trade.hystrix;

import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.trade.feign.service.FeignMessageQueueService;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class FeignMessageQueueServiceHystrix implements FeignMessageQueueService {

    @Override
    public ResultObject sendConfirmOrderStock(String orderId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }

    @Override
    public ResultObject sendCancelOrderStock(String orderId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }

    @Override
    public ResultObject sendDisassembleOrder(String orderId) {
        return new ResultObject(ErrorCode.HYSTRIX_ERROR);
    }

}
