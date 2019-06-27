package com.tg.shop.product.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.product.entity.Goods;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class WelcomeController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String home(){
        return "Welcome to "+applicationName;
    }

}
