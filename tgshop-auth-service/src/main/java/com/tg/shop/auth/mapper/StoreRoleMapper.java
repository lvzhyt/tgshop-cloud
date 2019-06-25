package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.StoreRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreRoleMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int deleteByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insert(StoreRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insertSelective(StoreRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    StoreRole selectByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKeySelective(StoreRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKey(StoreRole record);
}