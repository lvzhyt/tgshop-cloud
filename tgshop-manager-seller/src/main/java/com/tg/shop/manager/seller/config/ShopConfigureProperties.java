package com.tg.shop.manager.seller.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 店铺系统配置
 */
@Configuration
@ConfigurationProperties(prefix = "tgshop.conf")
public class ShopConfigureProperties {

    /**
     * 商品审核
     */
    private boolean goodsAuditEnable = true;

    public boolean isGoodsAuditEnable() {
        return goodsAuditEnable;
    }

    public void setGoodsAuditEnable(boolean goodsAuditEnable) {
        this.goodsAuditEnable = goodsAuditEnable;
    }
}
