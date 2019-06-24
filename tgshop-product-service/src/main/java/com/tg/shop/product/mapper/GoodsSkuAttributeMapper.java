package com.tg.shop.product.mapper;

import com.tg.shop.core.domain.product.entity.GoodsSkuAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsSkuAttributeMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int deleteByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insert(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int insertSelective(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    GoodsSkuAttribute selectByPrimaryKey(String tId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsSkuAttribute record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:31:39 CST 2019
     */
    int updateByPrimaryKey(GoodsSkuAttribute record);
}