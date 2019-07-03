package com.tg.shop.product.feign.service;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.product.hystrix.FeignMessageQueueControllerServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-mq-service",fallback = FeignMessageQueueControllerServiceHystrix.class)
public interface FeignMessageQueueControllerService {


    /**
     * Hello Feign
     * @param message
     * @return
     */
    @GetMapping("/mq/sendHelloMq")
    ResultObject sendHelloMq(@RequestParam String message);

    /**
     * 商品索引
     * @param goodsId
     * @return
     */
    @GetMapping("/mq/goodsEs")
    ResultObject goodsElasticSearch(@RequestParam String goodsId);
}
