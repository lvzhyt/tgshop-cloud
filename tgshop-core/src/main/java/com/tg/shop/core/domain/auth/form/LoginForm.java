package com.tg.shop.core.domain.auth.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: tg
 * @Date: 2019/3/7 15:43
 */
@Data
public class LoginForm implements Serializable {

    private String loginName;

    private String password;

    /**
     * 验证码
     */
    private String verificationCode;

    private String ip;

    private String imei;

    /**
     * 手机类型
     * 1 android  2 IOS
     */
    private Integer phoneType;

    private String phoneName;

}
