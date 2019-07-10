package com.tg.shop.auth;

import com.tg.shop.core.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: tg
 * @Date: 2019/3/7 17:39
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tg.shop.auth.mapper")
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
        System.out.println("=================================");
        System.out.println(SpringContextUtil.getApplicationContext());
    }
}
