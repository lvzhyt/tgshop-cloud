package com.tg.shop.core.domain.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/25 11:58
 */
@Data
public class PageResult<T> implements Serializable {

    private Pager pager;
    private List<T> list;

    public PageResult(){

    }

    public PageResult(PageInfo<T> pageInfo){
        this.pager = new Pager();
        this.pager.setPageNum(pageInfo.getPageNum());
        this.pager.setPageSize(pageInfo.getPageSize());
        this.pager.setPages(pageInfo.getPages());
        this.pager.setTotal(pageInfo.getTotal());
        this.list = pageInfo.getList();
    }
}
