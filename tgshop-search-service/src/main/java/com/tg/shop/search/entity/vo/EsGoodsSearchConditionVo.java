package com.tg.shop.search.entity.vo;

import lombok.Data;

/**
 * 查询条件vo
 * @author Administrator
 */
@Data
public class EsGoodsSearchConditionVo {


    /**
     * 商品名
     */
    private String search;

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

}
