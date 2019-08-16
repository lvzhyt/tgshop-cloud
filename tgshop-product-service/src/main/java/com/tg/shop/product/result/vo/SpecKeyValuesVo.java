package com.tg.shop.product.result.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecKeyValuesVo {

    private  String k;

    private List<SpecAttrVo> v = new ArrayList<>();

    private String k_s;
}
