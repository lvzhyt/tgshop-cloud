package com.tg.shop.product.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.auth.cache.SellerStore;
import com.tg.shop.core.domain.auth.entity.Member;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.utils.CacheMemberHolderLocal;
import com.tg.shop.core.utils.CacheSellerHolderLocal;
import com.tg.shop.core.utils.CacheStoreHolderLocal;
import com.tg.shop.core.utils.TokenHolderLocal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 认证拦截器
 * 获取redis的token对象,放到ThreadLocal
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
        String servletPath = request.getServletPath();
        JSONObject jsonObject = new JSONObject();
        if(StringUtils.isEmpty(token)) {
            return true;
        }
        if(!redisTemplate.hasKey(token)){
            return true;
        }
        Object object = redisTemplate.opsForValue().get(token);
        if(object!=null){
            if(object instanceof SellerStore){
                SellerStore sellerStore = (SellerStore) object;
                CacheSellerHolderLocal.setSeller(sellerStore.getSeller());
                CacheStoreHolderLocal.setStore(sellerStore.getStore());
                if(sellerStore.getStore()==null){
                    jsonObject.put("result", 0);
                    jsonObject.put("errorMessage", "没有创建店铺");
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    response.getWriter().write(jsonObject.toJSONString());
                    return false;
                }
            }else if(object instanceof Member){
                CacheMemberHolderLocal.setMember((Member) object);
            }else{
                redisTemplate.delete(token);
                ResultObject resultObject = new ResultObject(ErrorCode.TOKEN_LOSE_EFFICACY);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(resultObject));
                return false;
            }
        }
        TokenHolderLocal.setToken(token);
        // 更新超时时间 TimeUnit.MINUTES
        redisTemplate.expire(token,30, TimeUnit.DAYS);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        TokenHolderLocal.remove();
        CacheMemberHolderLocal.remove();
        CacheStoreHolderLocal.remove();
        CacheSellerHolderLocal.remove();
    }
}
