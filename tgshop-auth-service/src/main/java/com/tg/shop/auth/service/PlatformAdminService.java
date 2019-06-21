package com.tg.shop.auth.service;

import com.tg.shop.auth.request.LoginParam;

/**
 * @Author: tg
 * @Date: 2019/3/9 14:34
 */
public interface PlatformAdminService {

    /**
     * 登录
     * @param loginParam 登录表单
     * @return token
     */
    String login(LoginParam loginParam);


}
