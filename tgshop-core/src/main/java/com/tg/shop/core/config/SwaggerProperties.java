package com.tg.shop.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties(prefix = "tgshop.swagger")
public class SwaggerProperties {

    private boolean enable = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
