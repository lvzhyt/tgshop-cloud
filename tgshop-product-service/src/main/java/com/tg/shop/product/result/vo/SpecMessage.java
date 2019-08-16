package com.tg.shop.product.result.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品规格选择 商品留言
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecMessage {

    /**
     * 留言类型为 time 时，是否含日期。'1' 表示包含
     */
    private String datetime;

    /**
     *  留言类型为 text 时，是否多行文本。'1' 表示多行
     */
    private String multiple;

    /**
     *  留言名称
     */
    private String name;

    /**
     * 留言类型，可选: id_no（身份证）, text, tel, date, time, email
     */
    private String type;

    /**
     * 是否必填 '1' 表示必填
     */
    private String required;

    /**
     * 可选值，占位文本
     */
    private String placeholder;
}
