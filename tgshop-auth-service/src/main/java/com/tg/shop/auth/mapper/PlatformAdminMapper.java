package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.PlatformAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformAdminMapper {
    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int deleteByPrimaryKey(String adminId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insert(PlatformAdmin record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insertSelective(PlatformAdmin record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    PlatformAdmin selectByPrimaryKey(String adminId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKeySelective(PlatformAdmin record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKey(PlatformAdmin record);
}