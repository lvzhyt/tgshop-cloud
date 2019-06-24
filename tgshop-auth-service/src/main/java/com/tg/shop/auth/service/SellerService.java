package com.tg.shop.auth.service;

import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.entity.ResultObject;

/**
 * @Author: tg
 * @Date: 2019/3/9 16:49
 */
public interface SellerService {
    /**
     * 登录
     * 根据密码重新登录
     * @param loginForm
     * @return ResultState
     */
    ResultObject login(LoginParam loginForm);


    /**
     * 注册用
     * @param seller
     * @return ErrorMsg
     */
    String register(Seller seller);

    /**
     * 根据登录名/手机查找用户
     * @param loginName
     * @return
     */
    Seller findSellerByLoginName(String loginName);
}
