package com.tg.shop.auth.dao.impl;

import com.tg.shop.auth.dao.PlatformAdminDao;
import com.tg.shop.auth.mapper.PlatformAdminMapper;
import com.tg.shop.core.domain.auth.entity.PlatformAdmin;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/9 14:47
 */
@Repository
public class PlatformAdminDaoImpl implements PlatformAdminDao {

    @Resource
    private PlatformAdminMapper platformAdminMapper;

    @Override
    public PlatformAdmin findByLoginName(PlatformAdmin record) {
//        List<PlatformAdmin> list = platformAdminMapper.findListByRecord(record);
//        if(!list.isEmpty()){
//            return list.get(0);
//        }
        return null;
    }

    @Override
    public List<PlatformAdmin> findListByRecord(PlatformAdmin record) {
//        return platformAdminMapper.findListByRecord(record);
        return null;
    }
}
