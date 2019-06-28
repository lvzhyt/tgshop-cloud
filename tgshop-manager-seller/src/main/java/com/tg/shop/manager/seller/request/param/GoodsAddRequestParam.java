package com.tg.shop.manager.seller.request.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: tg
 * @Date: 2019/3/19 9:42
 */
@Data
@ApiModel("GoodsAddRequestParam")
public class GoodsAddRequestParam {

    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名称",required = true)
    @NotEmpty(message = "商品名称不能为空")
    private String goodsName;

    /**
     * 关键字
     */
    private String keywords;

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

}
