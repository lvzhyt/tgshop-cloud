package com.tg.shop.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tg.shop.mq.mapper")
@EnableFeignClients(basePackages = "com.tg.shop.mq.feign.service")
public class MqConsumerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqConsumerServiceApplication.class);
    }
}
