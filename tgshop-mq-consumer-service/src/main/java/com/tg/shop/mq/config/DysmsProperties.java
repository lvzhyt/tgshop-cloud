package com.tg.shop.mq.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * aliyun 大于 短信
 * @author Administrator
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.dysms")
@PropertySource("classpath:/dysms.properties")
public class DysmsProperties {

    private String accessKeyId;

    private String accessKeySecret;

}
