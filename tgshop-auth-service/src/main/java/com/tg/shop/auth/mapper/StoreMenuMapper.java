package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.StoreMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMenuMapper {
    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int deleteByPrimaryKey(String menuId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insert(StoreMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insertSelective(StoreMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    StoreMenu selectByPrimaryKey(String menuId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKeySelective(StoreMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKey(StoreMenu record);
}