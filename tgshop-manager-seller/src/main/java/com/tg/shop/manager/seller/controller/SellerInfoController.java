package com.tg.shop.manager.seller.controller;

import com.tg.shop.core.entity.ResultObject;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/seller")
public class SellerInfoController {


    @GetMapping("/messageCount")
    public ResultObject getUnReadMessageCount(){
        Integer count = RandomUtils.nextInt(0,100);
        return new ResultObject(count);
    }

}
