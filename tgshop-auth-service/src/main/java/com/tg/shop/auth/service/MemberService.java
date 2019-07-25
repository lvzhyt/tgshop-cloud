package com.tg.shop.auth.service;


import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.core.entity.ResultObject;

/**
 * @Author: tg
 * @Date: 2019/3/7 15:28
 */
public interface MemberService {

    /**
     * 登录
     * @param loginForm 登录表单
     * @return session token
     */
    String login(LoginParam loginForm);

    boolean logout(String loginName);

    /**
     * 通过phone 注册
     * @param loginForm
     * @return 错误信息
     */
    ResultObject registerByPhone(LoginParam loginForm);
}
