package com.tg.shop.auth.mapper;

import com.tg.shop.core.domain.auth.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int deleteByPrimaryKey(String memberId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insert(Member record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int insertSelective(Member record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    Member selectByPrimaryKey(String memberId);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     *
     * @mbg.generated Tue Jun 25 14:49:57 CST 2019
     */
    int updateByPrimaryKey(Member record);

    List<Member> findByRecord(Member record);
}