package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsSkuAttributeMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insert(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int insertSelective(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    GoodsSkuAttribute selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:55:57 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuAttribute record);

    List<GoodsSkuAttribute> findGoodsSkuAttributeList(GoodsSkuAttribute condition);
}