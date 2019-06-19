package com.tg.shop.controller;

import com.tg.shop.core.domain.product.entity.Goods;
import com.tg.shop.fegin.service.FeignExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class ConsumerController {

    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private FeignExampleService feignExampleService;

    @RequestMapping("/")
    public String welcome(){
        return "welcome to"+applicationName;
    }

    @GetMapping("/hello/{name}")
    public Goods index(@PathVariable("name") String name) {
        return feignExampleService.getGoods(name+", from "+applicationName);
    }
}
