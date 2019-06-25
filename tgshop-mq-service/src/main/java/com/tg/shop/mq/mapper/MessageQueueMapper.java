package com.tg.shop.mq.mapper;

import com.tg.shop.core.domain.mq.entity.MessageQueue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageQueueMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int deleteByPrimaryKey(String msgId);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int insert(MessageQueue record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int insertSelective(MessageQueue record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    MessageQueue selectByPrimaryKey(String msgId);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int updateByPrimaryKeySelective(MessageQueue record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int updateByPrimaryKey(MessageQueue record);
}