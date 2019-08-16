package com.tg.shop.search.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 商品列表查询结果
 * @author Administrator
 */
@Data
public class SearchGoodsListResultVo {

    private Integer pageNum;

    private Integer pageSize;

    private Long totalElements;

    private Integer totalPages;

    private List<SearchListGoodsItemVo> data;
}
