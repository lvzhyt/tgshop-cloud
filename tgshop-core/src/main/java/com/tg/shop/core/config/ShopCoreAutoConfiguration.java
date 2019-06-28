package com.tg.shop.core.config;


import com.tg.shop.core.exception.handler.GlobalDefaultExceptionHandler;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.generator.snowflake.SnowflakeIdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Administrator
 */
@Configuration
@EnableConfigurationProperties({SnowflakeGeneratorProperties.class,SwaggerProperties.class})
@Import({GlobalDefaultExceptionHandler.class})
public class ShopCoreAutoConfiguration {
    public ShopCoreAutoConfiguration() {
    }

    @Bean
    public IdGenerator idGenerator(SnowflakeGeneratorProperties properties){
        return new SnowflakeIdWorker(properties.getWorkerId(),properties.getDataCenter());
    }

}