package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int deleteByPrimaryKey(String brandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insert(Brand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insertSelective(Brand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    Brand selectByPrimaryKey(String brandId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKeySelective(Brand record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKey(Brand record);
}