package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PlatformRoleMenu implements Serializable {
    /**
     * role_menu_id
     */
    private String roleMenuId;

    /**
     * 角色Id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String menuId;

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