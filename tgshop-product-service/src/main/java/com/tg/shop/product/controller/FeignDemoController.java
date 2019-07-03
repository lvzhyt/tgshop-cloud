package com.tg.shop.product.controller;

import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.product.feign.service.FeignMessageQueueControllerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
public class FeignDemoController {

    @Resource
    private FeignMessageQueueControllerService feignMessageQueueControllerService;

    @GetMapping("/feign/mq/sendHelloMq")
    public ResultObject helloFeign(String message){
        return feignMessageQueueControllerService.sendHelloMq(message);
    }


    /**
     * 商品索引
     * @param goodsId
     * @return
     */
    @GetMapping("/feign/mq/goodsEs")
    public ResultObject goodsElasticSearch(@RequestParam String goodsId){
        return feignMessageQueueControllerService.goodsElasticSearch(goodsId);
    }
}
