package com.tg.shop.core.domain.product.result.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsSkuDetailResultVo implements Serializable {

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
     * SKU商品名称
     */
    private String skuGoodsName;

    private String storeId;

    private String storeName;

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


    private String specInfo;


    public String getSpecInfo() {

        this.specInfo="";
        if(this.colorAttrValName!=null){
            this.specInfo = this.colorAttrValName;
        }
        if(this.sizeAttrValName!=null){
            this.specInfo = this.colorAttrValName+"  "+this.sizeAttrValName;
        }

        return this.specInfo;
    }
}
