package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.StoreRoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreRoleMenuMapper {
    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int deleteByPrimaryKey(String roleMenuId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insert(StoreRoleMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insertSelective(StoreRoleMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    StoreRoleMenu selectByPrimaryKey(String roleMenuId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKeySelective(StoreRoleMenu record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKey(StoreRoleMenu record);
}