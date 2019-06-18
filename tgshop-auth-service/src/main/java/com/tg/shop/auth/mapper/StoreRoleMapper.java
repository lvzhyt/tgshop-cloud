package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.StoreRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreRoleMapper {
    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int deleteByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insert(StoreRole record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insertSelective(StoreRole record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    StoreRole selectByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKeySelective(StoreRole record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKey(StoreRole record);
}