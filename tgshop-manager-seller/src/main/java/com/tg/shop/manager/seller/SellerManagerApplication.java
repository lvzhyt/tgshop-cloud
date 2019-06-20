package com.tg.shop.manager.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SellerManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerManagerApplication.class);
    }
}
