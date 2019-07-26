package com.tg.shop.trade.mapper;

import com.tg.shop.core.domain.trade.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 用户购物车查询
     * @param memberId
     * @return
     */
    List<Cart> findCartByMember(String memberId);

    /**
     * 用户skuId查询
     * 唯一查询
     * @param memberId
     * @param skuId
     * @return
     */
    Cart findCartByMemberSkuId(@Param("memberId") String memberId, @Param("skuId") String skuId);
}