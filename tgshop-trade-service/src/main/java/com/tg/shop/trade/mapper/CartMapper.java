package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int deleteByPrimaryKey(String cartId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insert(Cart record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int insertSelective(Cart record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    Cart selectByPrimaryKey(String cartId);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     *
     * @mbg.generated Tue Jul 23 16:02:13 CST 2019
     */
    int updateByPrimaryKey(Cart record);
}