package com.tg.shop.mq.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqWelcomeController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to "+applicationName;
    }
}
