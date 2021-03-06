package com.tg.shop.categories.mapper;

import com.tg.shop.core.domain.categories.entity.AttributeValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttributeValueMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int deleteByPrimaryKey(String valueId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insert(AttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int insertSelective(AttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    AttributeValue selectByPrimaryKey(String valueId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKeySelective(AttributeValue record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:54:34 CST 2019
     */
    int updateByPrimaryKey(AttributeValue record);
}