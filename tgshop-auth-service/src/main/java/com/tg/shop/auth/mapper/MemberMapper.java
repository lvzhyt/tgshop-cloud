package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int deleteByPrimaryKey(String memberId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insert(Member record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int insertSelective(Member record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    Member selectByPrimaryKey(String memberId);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     *
     * @mbg.generated Thu Jun 20 14:59:35 CST 2019
     */
    int updateByPrimaryKey(Member record);
}