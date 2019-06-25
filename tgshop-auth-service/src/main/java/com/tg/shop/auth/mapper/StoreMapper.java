package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.Store;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int deleteByPrimaryKey(String storeId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insert(Store record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insertSelective(Store record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    Store selectByPrimaryKey(String storeId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKeySelective(Store record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKey(Store record);
}