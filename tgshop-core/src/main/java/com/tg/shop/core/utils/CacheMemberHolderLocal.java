package com.tg.shop.core.utils;

import com.tg.shop.core.domain.auth.entity.Member;

/**
 * @Author: tg
 * @Date: 2019/3/16 18:03
 */
public class CacheMemberHolderLocal {

    private static ThreadLocal<Member> local = new ThreadLocal<>();

    public static void setMember(Member member){
        local.set(member);
    }

    public static Member getMember(){
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
