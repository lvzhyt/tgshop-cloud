package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.PlatformAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformAdminMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int deleteByPrimaryKey(String adminId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insert(PlatformAdmin record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insertSelective(PlatformAdmin record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    PlatformAdmin selectByPrimaryKey(String adminId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKeySelective(PlatformAdmin record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKey(PlatformAdmin record);
}