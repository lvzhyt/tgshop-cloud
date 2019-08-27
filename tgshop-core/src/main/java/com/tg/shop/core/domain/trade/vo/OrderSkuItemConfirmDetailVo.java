package com.tg.shop.core.domain.trade.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单确认商品详情
 * @author Administrator
 */
@Data
public class OrderSkuItemConfirmDetailVo {

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
     * SKU商品名称
     */
    private String skuGoodsName;

    /**
     * 商品编号
     */
    private String skuNo;

    /**
     * 封面图url
     */
    private String specFacePictures;

    /**
     * 运费模板
     */
    private String templateId;

    /**
     * 运费
     */
    private String expressFee;


    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 促销价格
     */
    private BigDecimal promotePrice;

    /**
     * 库存
     */
    private Integer inventoryNum;

    /**
     * 规格信息
     */
    private String specInfo;

    /**
     * 购买数量
     */
    private Integer buyNum;

    /**
     * 购买价格
     */
    private BigDecimal buyPrice;

    /**
     * 优惠价格
     */
    private BigDecimal discountPrice;


    /**
     * 商品优惠总价
     */
    private BigDecimal discountAmount;

    /**
     * 商品sku总金额
     */
    private BigDecimal orderSkuItemAmount;

}
