package com.tg.shop.core.domain.product.entity;

import java.io.Serializable;
import java.util.Date;

import com.tg.shop.core.validate.InsertValid;
import com.tg.shop.core.validate.UpdateValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Goods implements Serializable {
    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID",required = true)
    @NotEmpty(groups = {UpdateValid.class},message = "商品Id不能为空")
    private String goodsId;

    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名称",required = true)
    @NotEmpty(groups = {InsertValid.class},message = "商品名称不能为空")
    private String goodsName;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 商品状态,1: 未发布，2：待审核，3：审核驳回，4：待上架，5：在售，6：已下架，7：锁定， 8： 申请解锁
     */
    private Integer goodsStatus;

    /**
     * 区域ID
     */
    private String areaId;

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
     * 上架时间
     */
    private Date listingTime;

    /**
     * 下架时间
     */
    private Date offShowTime;

    /**
     * 商品描述
     */
    private String describeUrl;

    /**
     * 商家ID
     */
    private String sellerId;

    /**
     * 店铺Id
     */
    private String storeId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 原产地
     */
    private String productOrigin;

    /**
     * 商品属性值JSON
     */
    private String itemAttributeJsonValue;

    /**
     * 商品销售属性值
     */
    private String itemSaleAttributeJsonValue;

    /**
     * 开启规格 1开启
     */
    private Integer specOpen;

    /**
     * 开启尺码属性 1开启
     */
    private Integer specSizeOpen;

    /**
     * 默认商品sku
     */
    private String defaultSkuId;

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