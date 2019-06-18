package com.tg.shop.core.domain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: tg
 * @Date: 2019/3/25 10:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCondition<T> implements Serializable {

    private T condition;

    private Integer pageNum;

    private Integer pageSize;
}
