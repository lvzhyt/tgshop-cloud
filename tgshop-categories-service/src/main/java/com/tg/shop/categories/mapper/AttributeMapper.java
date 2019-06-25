package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.Attribute;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttributeMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int deleteByPrimaryKey(String attrId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insert(Attribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insertSelective(Attribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    Attribute selectByPrimaryKey(String attrId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(Attribute record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKey(Attribute record);
}