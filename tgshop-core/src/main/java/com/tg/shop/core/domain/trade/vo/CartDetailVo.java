package com.tg.shop.core.domain.trade.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车商品详情
 * @author Administrator
 */
@Data
public class CartDetailVo {

    /**
     * 表id
     */
    private String cartId;

    /**
     * 会员
     */
    private String memberId;

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * sku_id
     */
    private String skuId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * spec_info
     */
    private String specInfo;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 加入购物车时
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * SKU商品名称
     */
    private String skuGoodsName;

    private String areaId;
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
     * 主图
     */
    private String pictureMain;

    /**
     * 详情图 商品描述
     */
    private String pictureDescription;

    /**
     * 运费模板
     */
    private String templateId;

    private BigDecimal weight;

    private BigDecimal volume;

    private String itemSize;

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
