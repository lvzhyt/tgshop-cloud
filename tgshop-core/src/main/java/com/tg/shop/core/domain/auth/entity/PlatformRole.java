package com.tg.shop.core.domain.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PlatformRole implements Serializable {
    /**
     * 
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String ruleName;

    /**
     * 角色描述
     */
    private String description;

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