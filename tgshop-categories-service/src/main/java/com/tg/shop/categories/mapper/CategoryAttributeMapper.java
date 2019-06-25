package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.CategoryAttribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryAttributeMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insert(CategoryAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insertSelective(CategoryAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    CategoryAttribute selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(CategoryAttribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKey(CategoryAttribute record);
}