package com.tg.shop.trade.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.annotation.AnonymousAccess;
import com.tg.shop.core.annotation.AuthToken;
import com.tg.shop.core.annotation.SellerToken;
import com.tg.shop.core.annotation.UserToken;
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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 认证拦截器
 * 拦截@UserToken  @SellerToken 注解
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
        System.out.println("path:"+servletPath);

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class klass = method.getDeclaringClass();

        if(klass.isAnnotationPresent(SellerToken.class)||method.isAnnotationPresent(SellerToken.class)){
            if(StringUtils.isEmpty(token)|| !redisTemplate.hasKey(token)){
                ErrorCode  errorCode = StringUtils.isEmpty(token)?ErrorCode.TOKEN_IS_EMPTY:ErrorCode.TOKEN_LOSE_EFFICACY;
                ResultObject resultObject = new ResultObject(errorCode);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(resultObject));
                return false;
            }
            Object object = redisTemplate.opsForValue().get(token);
            if(object instanceof SellerStore){
                SellerStore sellerStore = (SellerStore) object;
                CacheSellerHolderLocal.setSeller(sellerStore.getSeller());
                CacheStoreHolderLocal.setStore(sellerStore.getStore());
                if(sellerStore.getStore()==null){
                    ResultObject resultObject = new ResultObject("8001","没有创建店铺");
                    response.setHeader("Content-Type", "application/json;charset=UTF-8");
                    response.getWriter().write(JSONObject.toJSONString(resultObject));
                    return false;
                }
            }else{
                ResultObject resultObject = new ResultObject(ErrorCode.TOKEN_AUTH_POWER_ERROR);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(resultObject));
                return false;
            }
            TokenHolderLocal.setToken(token);
            // 更新超时时间 TimeUnit.MINUTES
            redisTemplate.expire(token,30, TimeUnit.DAYS);
            return true;

        }
        if (klass.isAnnotationPresent(UserToken.class)||method.isAnnotationPresent(UserToken.class)) {
            if(StringUtils.isEmpty(token)|| !redisTemplate.hasKey(token)){
                ErrorCode  errorCode = StringUtils.isEmpty(token)?ErrorCode.TOKEN_IS_EMPTY:ErrorCode.TOKEN_LOSE_EFFICACY;
                ResultObject resultObject = new ResultObject(errorCode);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(resultObject));
                return false;
            }
            Object object = redisTemplate.opsForValue().get(token);
            if(object instanceof Member){
                CacheMemberHolderLocal.setMember((Member) object);
            }else{
                // 非member的token访问 UserToken,返回权限不足
                ResultObject resultObject = new ResultObject(ErrorCode.TOKEN_AUTH_POWER_ERROR);
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(resultObject));
                return false;
            }
            TokenHolderLocal.setToken(token);
            // 更新超时时间 TimeUnit.MINUTES
            redisTemplate.expire(token,30, TimeUnit.DAYS);
            return true;
        }

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
