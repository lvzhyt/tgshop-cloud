package com.tg.shop.search.service;


import com.tg.shop.search.entity.EsGoods;
import com.tg.shop.search.entity.vo.EsGoodsSearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

public interface GoodsSearchService {

    /**
     * 查询
     * 综合排序
     * @param search
     * @return
     */
    Page<EsGoods> searchGoods(String search, int pageNum, int pageSize);

    /**
     * 查询排序
     * @param search
     * @param sortType 0 综合 1 销量 2 价格 3 条件筛选
     * @return
     */
    Page<EsGoods> searchGoods(String search, int sortType, int pageNum, int pageSize);

    /**
     * 条件查询
     * 品牌 分类
     * @param search
     * @param searchCondition
     * @return
     */
    Page<EsGoods> searchGoods(String search, EsGoodsSearchVo searchCondition, int pageNum, int pageSize);

    /**
     * 价格区间查询
     * @param search
     * @param minPrice
     * @param maxPrice
     * @return
     */
    Page<EsGoods> searchGoods(String search, double minPrice, double maxPrice, int pageNum, int pageSize);


    /**
     * 位置范围查询
     * @param search
     * @param location
     * @param offset
     * @return
     */
    Page<EsGoods> searchGoods(String search, GeoPoint location, double offset, int pageNum, int pageSize);

    void updateGoodsIndex(String goodsId);

}
