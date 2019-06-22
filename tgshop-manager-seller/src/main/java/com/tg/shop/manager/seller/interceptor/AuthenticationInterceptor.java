package com.tg.shop.manager.seller.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.auth.cache.SellerUser;
import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import com.tg.shop.core.utils.RedisUtil;
import com.tg.shop.core.utils.TokenHolderLocal;
import com.tg.shop.manager.seller.utils.CacheSellerHolderLocal;
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
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String servletPath = request.getServletPath();
        String token = request.getHeader("token");
        JSONObject jsonObject = new JSONObject();
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
        SellerUser sellerUser = (SellerUser) redisTemplate.opsForValue().get(token);
        if((!servletPath.equals("/api/store/add")) && sellerUser.getStore()==null){
            jsonObject.put("result", 0);
            jsonObject.put("errorMessage", "没有创建店铺");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write(jsonObject.toJSONString());
            return false;
        }
        TokenHolderLocal.setToken(token);
        CacheSellerHolderLocal.setSellerUser(sellerUser);
        // 更新超时时间 TimeUnit.MINUTES
        redisTemplate.expire(token,30, TimeUnit.DAYS);
        String keyLogin = RedisUtil.getSellerKey(sellerUser.getSeller().getLoginName());
        stringRedisTemplate.expire(keyLogin,30, TimeUnit.DAYS);
        String keyPhone = RedisUtil.getSellerKey(sellerUser.getSeller().getPhone());
        stringRedisTemplate.expire(keyPhone,30, TimeUnit.DAYS);

        return true;
    }
}
