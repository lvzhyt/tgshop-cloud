package com.tg.shop.categories.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
