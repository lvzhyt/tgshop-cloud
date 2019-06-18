package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.Seller;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMapper {
    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int deleteByPrimaryKey(String sellerId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insert(Seller record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int insertSelective(Seller record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    Seller selectByPrimaryKey(String sellerId);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKeySelective(Seller record);

    /**
     *
     * @mbg.generated Tue Apr 16 17:57:16 CST 2019
     */
    int updateByPrimaryKey(Seller record);

    List<Seller> findListByRecord(Seller record);
}