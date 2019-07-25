package com.tg.shop.auth.feign.service;

import com.tg.shop.auth.hystrix.FeignMessageQueueServiceHystrix;
import com.tg.shop.core.entity.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(value = "tgshop-mq-producer-service",fallback = FeignMessageQueueServiceHystrix.class)
public interface FeignMessageQueueService {


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
