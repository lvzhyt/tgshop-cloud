package com.tg.shop.core.domain.upload;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileUpload implements Serializable {
    /**
     * 上传文件id 主键
     */
    private String uploadFileId;

    /**
     * 店铺id  用户id
     */
    private String ownerId;

    /**
     * 相关id 商品id 
     */
    private String refId;

    /**
     * 类型
     */
    private Integer uploadType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 图片url
     */
    private String fileUrl;

    /**
     * 源文件md5
     */
    private String srcMd5;

    /**
     * 文件MD5
     */
    private String fileMd5;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 文件类型
     */
    private String fileExt;

    /**
     * 备注
     */
    private String remark;

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