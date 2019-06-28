package com.tg.shop.categories;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.tg.shop.categories.mapper")
@EnableSwagger2
public class CategoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class);
    }
}
