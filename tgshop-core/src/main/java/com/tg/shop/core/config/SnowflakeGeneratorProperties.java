package com.tg.shop.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 */
@ConfigurationProperties(
        prefix = "generator.snowflake"
)
public class SnowflakeGeneratorProperties {

    private int workerId =0;

    private int dataCenter =0;


    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public int getDataCenter() {
        return dataCenter;
    }

    public void setDataCenter(int dataCenter) {
        this.dataCenter = dataCenter;
    }
}
