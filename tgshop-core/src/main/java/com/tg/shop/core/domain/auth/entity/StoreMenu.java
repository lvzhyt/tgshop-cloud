package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class StoreMenu implements Serializable {
    /**
     * 
     */
    private String menuId;

    /**
     * 
     */
    private String storeId;

    /**
     * 
     */
    private String menuName;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 等级
     */
    private Integer memuLevel;

    /**
     * 路径
     */
    private String idPath;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限
     */
    private String permission;

    /**
     * 是否显示：0,否;1,是
     */
    private Integer isShow;

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