package com.tg.shop.auth.dao.impl;

import com.tg.shop.auth.dao.MemberDao;
import com.tg.shop.auth.mapper.MemberMapper;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/8 11:51
 */
@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    private IdGenerator idGenerator;

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int saveMember(Member member) {
        member.setMemberId(idGenerator.nextStringId());
        return memberMapper.insertSelective(member);
    }

    @Override
    public Member findMemberByLoginName(String loginName) {
        Member record = new Member();
        if(RegexUtil.isMobile(loginName)){
            record.setPhone(loginName);
        }else{
            record.setLoginName(loginName);
        }
        List<Member> list = memberMapper.findByRecord(record);
        if(!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Member> findByRecord(Member record) {
//        return memberMapper.findByRecord(record);
        return null;
    }
}
