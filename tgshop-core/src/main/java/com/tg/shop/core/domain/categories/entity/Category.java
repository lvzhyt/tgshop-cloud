package com.tg.shop.core.domain.categories.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Category implements Serializable {
    /**
     * 类目ID
     */
    private String categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 是否包含叶子结点,0 否 1 是
     */
    private Integer hasLeaf;

    /**
     * 1:一级类目;2:二级类目;3:三级类目
     */
    private Integer lev;

    /**
     * 父类目ID
     */
    private String parentCategoryId;

    /**
     * 排序 排序号越小越靠前
     */
    private Integer sortNumber;

    /**
     * 首页显示 1:是;2:否
     */
    private Integer homeShow;

    /**
     * 到跟节点路径id,分隔
     */
    private String rootPath;

    /**
     * 路径值JSON
     */
    private String rootPathJson;

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