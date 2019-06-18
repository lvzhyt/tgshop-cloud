package com.tg.shop.auth;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: tg
 * @Date: 2019/3/7 17:39
 */
@SpringBootApplication
@EnableDubbo
@ComponentScan({"com.tg.shop.auth","com.tg.shop.core"})
@MapperScan("com.tg.shop.auth.mapper")
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
    }
}
