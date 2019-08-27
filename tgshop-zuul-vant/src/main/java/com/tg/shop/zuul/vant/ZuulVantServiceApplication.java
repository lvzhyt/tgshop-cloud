package com.tg.shop.zuul.vant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulVantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulVantServiceApplication.class);
    }
}
