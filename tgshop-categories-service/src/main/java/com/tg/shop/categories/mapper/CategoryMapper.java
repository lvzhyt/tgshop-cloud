package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int deleteByPrimaryKey(String categoryId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insert(Category record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int insertSelective(Category record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    Category selectByPrimaryKey(String categoryId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:27:47 CST 2019
     */
    int updateByPrimaryKey(Category record);
}