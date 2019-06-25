package com.tg.shop.mq.mapper;

import com.tg.shop.core.domain.mq.entity.MessageQueueLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageQueueLogMapper {
    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int deleteByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int insert(MessageQueueLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int insertSelective(MessageQueueLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    MessageQueueLog selectByPrimaryKey(String tbId);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int updateByPrimaryKeySelective(MessageQueueLog record);

    /**
     *
     * @mbg.generated Tue Jun 25 20:26:39 CST 2019
     */
    int updateByPrimaryKey(MessageQueueLog record);
}