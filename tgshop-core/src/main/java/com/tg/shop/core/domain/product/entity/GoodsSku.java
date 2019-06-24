package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class GoodsSku implements Serializable {
    /**
     * 表id
     */
    private String skuId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 卖家ID
     */
    private String sellerId;

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
     * 商品编号
     */
    private String skuNo;

    /**
     * 销售属性JSON
     */
    private String attrValueJson;

    /**
     * 颜色属性id
     */
    private String colorAttrValId;

    /**
     * 颜色属性值名称
     */
    private String colorAttrValName;

    /**
     * 尺码属性值id
     */
    private String sizeAttrValId;

    /**
     * 尺码属性值名称
     */
    private String sizeAttrValName;

    /**
     * sku类型 1:主sku,2:非主sku
     */
    private Integer skuType;

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

    /**
     * 体积
     */
    private BigDecimal volume;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 尺寸
     */
    private String itemSize;

    /**
     * 删除状态
     */
    private Integer isDel;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}