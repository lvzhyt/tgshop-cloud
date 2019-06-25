package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.PlatformRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformRoleMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int deleteByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insert(PlatformRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insertSelective(PlatformRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    PlatformRole selectByPrimaryKey(String roleId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKeySelective(PlatformRole record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKey(PlatformRole record);
}