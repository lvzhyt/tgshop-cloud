package com.tg.shop.product.result.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sku 的组合
 * 比如红色、M 码为一个 sku 组合，红色、S 码为另一个组合
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuSpecItemVo {

    /**
     * skuId，下单时后端需要
     */
    private String id;

    /**
     * 价格（单位分）
     */
    private int price;

    /**
     * 规格类目 k_s 为 s1 的对应规格值 id
     */
    private String s1;

    /**
     * 规格类目 k_s 为 s2 的对应规格值 id
     */
    private String s2;

    /**
     * 最多包含3个规格值，为0表示不存在该规格
     */
    private String s3="0";

    /**
     * 当前 sku 组合对应的库存
     */
    private int stock_num;
}
