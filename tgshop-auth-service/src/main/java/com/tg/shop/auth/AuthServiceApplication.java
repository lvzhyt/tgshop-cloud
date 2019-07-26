package com.tg.shop.auth;

import com.tg.shop.core.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: tg
 * @Date: 2019/3/7 17:39
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tg.shop.auth.mapper")
@EnableFeignClients(basePackages = "com.tg.shop.auth.feign.service")
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class);
    }
}
