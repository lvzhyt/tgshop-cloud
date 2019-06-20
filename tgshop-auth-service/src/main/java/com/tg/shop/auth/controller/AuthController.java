package com.tg.shop.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class AuthController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to "+applicationName;
    }
}
