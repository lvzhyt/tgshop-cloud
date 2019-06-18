package com.tg.shop.auth.dao;

import com.tg.shop.core.domain.auth.entity.Member;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/8 11:51
 */
public interface MemberDao {
    int saveMember(Member member);

    Member findMemberByLoginName(String loginName);

    List<Member> findByRecord(Member record);
}
