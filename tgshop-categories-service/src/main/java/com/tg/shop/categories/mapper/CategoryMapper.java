package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int deleteByPrimaryKey(String categoryId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insert(Category record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insertSelective(Category record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    Category selectByPrimaryKey(String categoryId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKey(Category record);
}