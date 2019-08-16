package com.tg.shop.product.result.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 规格选择  SKU信息
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSkuSpecDetailVo {
    /**
     * 无规格商品 skuId 取 collection_id，否则取所选 sku 组合对应的 id
     */
    private String collection_id;

    /**
     * 默认价格（单位元）
     */
    private BigDecimal price;

    /**
     * 商品总库存
     */
    private int stock_num;

    /**
     * 是否无规格商品
     */
    private boolean none_sku;

    private List<SpecKeyValuesVo> tree;

    private List<SkuSpecItemVo> list;

    private List message = new ArrayList();

    /**
     * 是否隐藏剩余库存
     */
    private boolean hide_stock;

}
