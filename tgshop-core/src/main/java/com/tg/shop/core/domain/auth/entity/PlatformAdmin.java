package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PlatformAdmin implements Serializable {
    /**
     * 
     */
    private String adminId;

    /**
     * 会员名称
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 超级管理员 1是 0 否
     */
    private Integer superAdmin;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 邮箱验证 0未验证 1已验证
     */
    private Integer emailValid;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 会员等级 0 普通 1: vip1 2:vip2 10:plus
     */
    private Integer grade;

    /**
     * plus会员 0 不是 1 是
     */
    private Integer plus;

    /**
     * 绑定微信
     */
    private String weichat;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 身份认证 0未验证 1已验证
     */
    private Integer idCardValid;

    /**
     *  状态 0 正常 1 禁止登录
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 删除状态
     */
    private Integer isDel;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 版本号
     */
    private Integer version;

    private static final long serialVersionUID = 1L;
}