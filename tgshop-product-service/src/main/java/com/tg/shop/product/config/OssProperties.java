package com.tg.shop.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: tg
 * @Date: 2018/12/20 11:45
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {


    private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private String accessKeyId = "<yourAccessKeyId>";
    private String accessKeySecret = "<yourAccessKeySecret>";
    private String bucketName="<yourBucketName>";
    private String pubService="<yourPubService>";

}
