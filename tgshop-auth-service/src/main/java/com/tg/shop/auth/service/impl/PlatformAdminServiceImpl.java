package com.tg.shop.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tg.shop.auth.dao.PlatformAdminDao;
import com.tg.shop.auth.service.PlatformAdminService;
import com.tg.shop.core.domain.auth.entity.PlatformAdmin;
import com.tg.shop.core.domain.auth.form.LoginForm;
import com.tg.shop.core.utils.RedisUtil;
import com.tg.shop.core.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tg
 * @Date: 2019/3/9 14:36
 */
@Service
public class PlatformAdminServiceImpl implements PlatformAdminService {

    @Autowired
    private PlatformAdminDao platformAdminDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(LoginForm loginForm) {
        Assert.notNull(loginForm.getLoginName(),"admin loginName is null");
        Assert.notNull(loginForm.getPassword(),"admin password is null");
        String loginName = loginForm.getLoginName();
        String key = RedisUtil.PRE_KEY_ADMIN +loginName;
        String token = stringRedisTemplate.opsForValue().get(key);
        if(token!=null){
            return token;
        }


        PlatformAdmin record = new PlatformAdmin();
        if(RegexUtil.isMobile(loginName)){
            record.setPhone(loginName);
        }else{
            record.setLoginName(loginName);
        }
        PlatformAdmin admin = platformAdminDao.findByLoginName(record);
        if(admin!=null){
            String passwdMd5 = DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes());
            String dbPasswd = admin.getPassword();
            if(passwdMd5.equals(dbPasswd)){
                token = UUID.randomUUID().toString().replace("-","");
                stringRedisTemplate.opsForValue().set(key,token,30, TimeUnit.DAYS);
                redisTemplate.opsForValue().set(token,admin,30, TimeUnit.DAYS);
            }
        }

        return token;
    }
}
