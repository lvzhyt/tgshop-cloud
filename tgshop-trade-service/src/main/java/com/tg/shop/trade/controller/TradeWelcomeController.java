package com.tg.shop.trade.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class TradeWelcomeController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to "+applicationName;
    }
}
