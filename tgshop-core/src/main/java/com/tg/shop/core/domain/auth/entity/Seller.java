package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Seller implements Serializable {
    /**
     * 主键
     */
    private String sellerId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 店铺角色id
     */
    private String roleId;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 卖家名称
     */
    private String sellerName;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 是否主账号 1是 拥有最高权限
     */
    private Integer isSellerAdmin;

    /**
     * 性别 0 未设置 1男 2女
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
     * 删除状态 1 删除
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