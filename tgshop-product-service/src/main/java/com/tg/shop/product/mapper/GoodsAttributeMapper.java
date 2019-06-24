package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsAttributeMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(GoodsAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(GoodsAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    GoodsAttribute selectByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(GoodsAttribute record);
}