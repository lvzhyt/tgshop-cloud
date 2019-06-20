package com.tg.shop.manager.seller.controller;

import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.User;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.manager.seller.utils.CacheSellerHolderLocal;
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
        return ResultObject.getInstance(count);
    }

}
