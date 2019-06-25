package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSku;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuDetailResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuInventoryResultVo;
import com.tg.shop.core.domain.product.result.vo.GoodsSkuPriceResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSkuMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsSku record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsSku record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsSku selectByPrimaryKey(String skuId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSku record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsSku record);


    List<GoodsSku> findSkuByCondition(GoodsSku condition);

    List<GoodsSkuInventoryResultVo> findSkuInventoryListByGoodsId(String goodsId);

    List<GoodsSkuPriceResultVo> findSkuPriceListByGoodsId(String goodsId);

    List<GoodsSkuDetailResultVo> findSkuDetailListByGoodsId(String goodsId);
}