package com.tg.shop.auth.dao;

import com.tg.shop.core.domain.auth.entity.PlatformAdmin;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/9 14:47
 */
public interface PlatformAdminDao {

    PlatformAdmin findByLoginName(PlatformAdmin record);

    List<PlatformAdmin> findListByRecord(PlatformAdmin record);

}
