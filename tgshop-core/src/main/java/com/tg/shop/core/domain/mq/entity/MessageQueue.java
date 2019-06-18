package com.tg.shop.core.domain.mq.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class MessageQueue implements Serializable {
    /**
     * 消息id 主键
     */
    private String msgId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 关联键
     */
    private String refKey;

    /**
     * 消息投递号
     */
    private Long deliveryTag;

    /**
     * 投递次数
     */
    private Integer deliveryNum;

    /**
     * 投递时间
     */
    private Date deliveryTime;

    /**
     * 消息状态
     */
    private Integer msgState;

    /**
     * 消息数据json
     */
    private String messageData;

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