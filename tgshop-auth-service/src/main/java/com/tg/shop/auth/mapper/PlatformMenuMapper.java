package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.PlatformMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlatformMenuMapper {
    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int deleteByPrimaryKey(String menuId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insert(PlatformMenu record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insertSelective(PlatformMenu record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    PlatformMenu selectByPrimaryKey(String menuId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKeySelective(PlatformMenu record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKey(PlatformMenu record);
}