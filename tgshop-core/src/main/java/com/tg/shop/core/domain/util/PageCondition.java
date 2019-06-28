package com.tg.shop.core.domain.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: tg
 * @Date: 2019/3/25 10:23
 */

public class PageCondition<T> implements Serializable {

    private T condition;

    private Integer pageNum = 1;

    private Integer pageSize= 10;

    public PageCondition() {
    }

    public PageCondition(T condition, Integer pageNum, Integer pageSize) {
        this.condition = condition;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
