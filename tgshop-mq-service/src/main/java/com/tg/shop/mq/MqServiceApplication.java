package com.tg.shop.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tg.shop.mq.mapper")
public class MqServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqServiceApplication.class);
    }
}
