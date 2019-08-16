package com.tg.shop.search.entity.vo;

import lombok.Data;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class SkuDetailResultVo implements Serializable {

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
     * 关键字
     */
    private String keywords;

    /**
     * 商品状态,1:未发布，2：待审核，20：审核驳回，3：待上架，4：在售，5：已下架，6：锁定， 7： 申请解锁,8: 删除
     */
    private Integer goodsStatus;


    /**
     * 品牌
     */
    private String brandId;

    /**
     * 类目
     */
    private String categoryId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 类目名
     */
    private String categoryName;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 原产地
     */
    private String productOrigin;

    /**
     * 开启规格 1开启
     */
    private Integer specOpen;

    /**
     * 开启尺码属性 1开启
     */
    private Integer specSizeOpen;


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
     * 运费模板
     */
    private String templateId;

    /**
     * 运费
     */
    private String expressFee;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 位置
     */
    private GeoPoint location;


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
     * 文本详情描述
     */
    private String textDescription;

    /**
     * 逗号分隔
     */
    private List<String> tagList = new ArrayList<>();

    /**
     * 主图
     */
    private List<String> pictureMainList = new ArrayList<>();

    /**
     * 详情图 商品描述
     */
    private List<String> pictureDescriptionList = new ArrayList<>();

}
