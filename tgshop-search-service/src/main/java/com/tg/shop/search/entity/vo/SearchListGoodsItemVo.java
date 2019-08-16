package com.tg.shop.search.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品列表查询结果
 * @author Administrator
 */
@Data
public class SearchListGoodsItemVo {


    /**
     * 表id
     */
    private String skuId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名
     */
    private String goodsName;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺ID
     */
    private String storeId;

    /**
     * 区域id
     */
    private String areaId;

    /**
     * SKU商品名称
     */
    private String skuGoodsName;


    /**
     * 封面图url
     */
    private String specFacePictures;


    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 促销价格
     */
    private BigDecimal promotePrice;

    /**
     * 开启团购
     */
    private Integer groupBuyOpen;

    /**
     * 团购价格
     */
    private BigDecimal groupBuyPrice;

    /**
     * 库存
     */
    private Integer inventoryNum;


    /**
     * 距离
     */
    private Double locationOffset;


    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 是否广告 1 开启
     */
    private Integer advertAble;

    /**
     * 标签 逗号分隔
     */
    private String tags;
}
