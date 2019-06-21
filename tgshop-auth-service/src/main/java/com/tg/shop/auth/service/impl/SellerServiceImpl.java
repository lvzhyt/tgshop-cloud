package com.tg.shop.auth.service.impl;

import com.tg.shop.auth.dao.SellerDao;
import com.tg.shop.auth.mapper.SellerMapper;
import com.tg.shop.auth.mapper.StoreMapper;
import com.tg.shop.auth.request.LoginParam;
import com.tg.shop.auth.service.SellerService;
import com.tg.shop.core.domain.ResultState;
import com.tg.shop.core.domain.auth.cache.SellerUser;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.generator.IdGenerator;
import com.tg.shop.core.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tg
 * @Date: 2019/3/9 16:51
 */
@Service
public class SellerServiceImpl implements SellerService {


    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IdGenerator idGenerator;


    @Override
    public ResultState login(LoginParam loginForm) {
        Assert.notNull(loginForm.getUserName(),"seller loginName is null");
        Assert.notNull(loginForm.getPassword(),"seller password is null");
        String loginName = loginForm.getUserName();
        String key = RedisUtil.getSellerKey(loginName);
        String token = stringRedisTemplate.opsForValue().get(key);
        if(token!=null){
            return new ResultState(token);
        }

        Seller seller = sellerDao.findSellerByLoginName(loginName);


        if(seller!=null){
            String passwdMd5 = DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes());
            String dbPasswd = seller.getPassword();
            if(passwdMd5.equals(dbPasswd)){
                token = UUID.randomUUID().toString().replace("-","");
                String keyLogin = RedisUtil.getSellerKey(seller.getLoginName());
                String keyPhone = RedisUtil.getSellerKey(seller.getPhone());
                stringRedisTemplate.opsForValue().set(keyLogin,token,30, TimeUnit.DAYS);
                stringRedisTemplate.opsForValue().set(keyPhone,token,30, TimeUnit.DAYS);
                SellerUser sellerUser = new SellerUser();
                sellerUser.setSeller(seller);
                // 查找店铺
                if(seller.getStoreId()!=null){
                    Store store = storeMapper.selectByPrimaryKey(seller.getStoreId());
                    sellerUser.setStore(store);
                }
                // 放入redis缓存
                redisTemplate.opsForValue().set(token, sellerUser,30, TimeUnit.DAYS);
            }else {
                return new ResultState(ResultState.MESSAGE_TYPE_ERROR,"密码不正确");
            }
        }else {
            return new ResultState(ResultState.MESSAGE_TYPE_ERROR,"用户不存在");
        }

        return new ResultState(token);
    }

    @Override
    public String register(Seller seller) {
        if(StringUtils.isEmpty(seller.getLoginName())&&
                StringUtils.isEmpty(seller.getPhone())){
            throw new IllegalArgumentException("注册用户名/手机为空");
        }
        int result = sellerMapper.insertSelective(seller);
        String errorMsg = result>0?null:"注册插入数据失败.";
        return errorMsg;
    }

    @Override
    public Seller findSellerByLoginName(String loginName) {
        return sellerDao.findSellerByLoginName(loginName);
    }

}
