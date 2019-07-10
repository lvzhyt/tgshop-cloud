package com.tg.shop.manager.seller.utils;

import com.tg.shop.core.domain.auth.cache.SellerStore;
import com.tg.shop.core.domain.auth.entity.Seller;
import com.tg.shop.core.domain.auth.entity.Store;
import com.tg.shop.core.utils.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

public class RedisCacheUtil {

    /**
     * 获取 SellerStore
     * @param token
     * @return
     */
    public static SellerStore getCacheTokenObject(String token){
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Assert.notNull(applicationContext,"ApplicationContext is null");
        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        if(redisTemplate==null){
            throw new RuntimeException("RedisTemplate is not autowired.");
        }
        if(!redisTemplate.hasKey(token)){
            throw new RuntimeException("token is not cached.");
        }
        Object object = redisTemplate.opsForValue().get(token);
        if(!(object instanceof SellerStore)){
            redisTemplate.delete(token);
            throw new RuntimeException("object is not instance of SellerStore");
        }
        SellerStore sellerStore = (SellerStore) object;
        return sellerStore;
    }

    /**
     * 获取Seller
     * @param token
     * @return
     */
    public static Seller getCacheSeller(String token){
        SellerStore sellerStore = getCacheTokenObject(token);
        return sellerStore.getSeller();
    }

    public static Store getCacheStore(String token){
        SellerStore sellerStore = getCacheTokenObject(token);
        return sellerStore.getStore();
    }
}
