package com.tg.shop.auth.controller;

import com.tg.shop.core.generator.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
public class AuthController {

    @Autowired
    private IdGenerator idGenerator;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to "+applicationName +" "+ idGenerator.nextStringId();
    }
}
