package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.Store;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    int deleteByPrimaryKey(String storeId);

    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    int insert(Store record);

    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    int insertSelective(Store record);

    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    Store selectByPrimaryKey(String storeId);

    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    int updateByPrimaryKeySelective(Store record);

    /**
     *
     * @mbg.generated Wed Apr 17 09:52:29 CST 2019
     */
    int updateByPrimaryKey(Store record);
}