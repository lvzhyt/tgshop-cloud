package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String goodsId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(Goods record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(Goods record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    Goods selectByPrimaryKey(String goodsId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(Goods record);

    List<Goods> findGoodsList(Goods condition);

    List<Goods> findGoodsWithSkuListPageList(Goods condition);
}