package com.tg.shop.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 店铺系统配置
 */
@Data
@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "shop.conf")
public class ShopConfigureProperties {

    /**
     * 商品审核
     */
    private boolean goodsAuditEnable = true;

}
