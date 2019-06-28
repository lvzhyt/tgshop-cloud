package com.tg.shop.manager.seller.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.auth.cache.SellerStore;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.utils.CacheSellerHolderLocal;
import com.tg.shop.core.utils.CacheStoreHolderLocal;
import com.tg.shop.core.utils.TokenHolderLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 认证拦截器
 * @Author: tg
 * @Date: 2019/3/18 15:44
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)) {
            ResultObject resultObject = new ResultObject(ErrorCode.TOKEN_IS_EMPTY);
            response.getWriter().write(JSONObject.toJSONString(resultObject));
            return false;
        }
        if(!redisTemplate.hasKey(token)){
            ResultObject resultObject = new ResultObject(ErrorCode.TOKEN_LOSE_EFFICACY);
            response.getWriter().write(JSONObject.toJSONString(resultObject));
            return false;
        }
        SellerStore sellerStore = (SellerStore) redisTemplate.opsForValue().get(token);
        TokenHolderLocal.setToken(token);
        CacheSellerHolderLocal.setSeller(sellerStore.getSeller());
        CacheStoreHolderLocal.setStore(sellerStore.getStore());

        // 更新超时时间 TimeUnit.MINUTES
        redisTemplate.expire(token,30, TimeUnit.DAYS);

        return true;
    }
}
