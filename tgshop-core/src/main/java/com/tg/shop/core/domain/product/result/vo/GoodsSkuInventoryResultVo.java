package com.tg.shop.core.domain.product.result.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * seller 设置库存列表
 */
@Data
public class GoodsSkuInventoryResultVo implements Serializable {

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
     * 库存数量
     */
    private Integer inventoryNum;

    /**
     * 锁定数量
     */
    private Integer lockNum;

    /**
     * 可用数量
     */
    private Integer leftNum;
}
