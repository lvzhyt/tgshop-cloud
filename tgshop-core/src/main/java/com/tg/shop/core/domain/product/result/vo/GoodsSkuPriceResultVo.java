package com.tg.shop.core.domain.product.result.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * seller 设置价格列表
 */
@Data
public class GoodsSkuPriceResultVo implements Serializable {

    /**
     * 表id
     */
    private String skuId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品编号
     */
    private String skuNo;


    /**
     * 颜色属性值名称
     */
    private String colorAttrValName;

    /**
     * 尺码属性值名称
     */
    private String sizeAttrValName;

    /**
     * SKU状态  1有效   2无效
     */
    private Integer skuStatus;

    /**
     * 封面图url
     */
    private String specFacePictures;


    /**
     * 成本价格
     */
    private BigDecimal costPrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 开启plus价格 0 否 1是
     */
    private Integer plusPriceOpen;

    /**
     * plus价格
     */
    private BigDecimal plusPrice;

    /**
     * 开启超级会员价 0 否 1是
     */
    private Integer superVipPriceOpen;

    /**
     * 超级会员价
     */
    private BigDecimal superVipPrice;

}
