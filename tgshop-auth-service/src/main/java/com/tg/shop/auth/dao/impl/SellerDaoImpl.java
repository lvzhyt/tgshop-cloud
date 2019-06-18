package com.tg.shop.auth.dao.impl;

import com.tg.shop.auth.dao.SellerDao;
import com.tg.shop.auth.mapper.SellerMapper;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.utils.RegexUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/9 17:05
 */
@Repository
public class SellerDaoImpl implements SellerDao {

    @Resource
    private SellerMapper sellerMapper;

    @Override
    public Seller findSellerByLoginName(String loginName) {
        Seller record = new Seller();
        if(RegexUtil.isMobile(loginName)){
            record.setPhone(loginName);
        }else {
            record.setLoginName(loginName);
        }
        List<Seller> list = sellerMapper.findListByRecord(record);
        if(!list.isEmpty()){
            return list.get(0);
        }

        return null;
    }

    @Override
    public List<Seller> findListByRecord(Seller record) {
//        return sellerMapper.findListByRecord(record);
        return null;
    }
}
