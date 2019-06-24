package com.tg.shop.mq.mapper;

import com.tg.shop.core.domain.mq.entity.MessageQueue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageQueueMapper {
    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    int deleteByPrimaryKey(String msgId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    int insert(MessageQueue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    int insertSelective(MessageQueue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    MessageQueue selectByPrimaryKey(String msgId);

    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    int updateByPrimaryKeySelective(MessageQueue record);

    /**
     *
     * @mbg.generated Sun Jun 23 01:33:35 CST 2019
     */
    int updateByPrimaryKey(MessageQueue record);
}