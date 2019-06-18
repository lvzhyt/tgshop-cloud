package com.tg.shop.core.domain.search;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Document(indexName = "tgshop",type = "goods")
public class EsGoods implements Serializable {

    /**
     * 表id
     */
    @Id
    private String skuId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String goodsName;

    /**
     * 关键字
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
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
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String brandName;

    /**
     * 类目名
     */
    @Field(type = FieldType.Keyword)
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
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String skuGoodsName;

    /**
     * 商品编号
     */
    @Field(type = FieldType.Keyword)
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
    @Field(type = FieldType.Keyword)
    private String colorAttrValName;

    /**
     * 尺码属性值id
     */
    private String sizeAttrValId;

    /**
     * 尺码属性值名称
     */
    @Field(type = FieldType.Keyword)
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
    @Field(type = FieldType.Keyword,index = false)
    private String specFacePictures;

    /**
     * 主图
     */
    @Field(type = FieldType.Keyword,index = false)
    private String pictureMain;

    /**
     * 详情图 商品描述
     */
    @Field(type = FieldType.Keyword,index = false)
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
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 库存
     */
    private Integer inventoryNum;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 位置
     */
    @GeoPointField
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
     * 广告排序分数
     * 分值越大越靠前
     */
    private Integer advertSortScore;

}
