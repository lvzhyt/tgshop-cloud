package com.tg.shop.core.utils;

import com.tg.shop.core.domain.CacheUser;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class CacheUtil {

    public static final String KEY_CACHE_USER = "cache_user";
    public static CacheUser getCacheUser(){

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true);
        if (null != session) {
            return (CacheUser) session.getAttribute(CacheUtil.KEY_CACHE_USER);
        }

        return new CacheUser();
    }

    public static void setCacheUser(CacheUser cacheUser){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(true);
        if (null != session) {
            session.setAttribute(CacheUtil.KEY_CACHE_USER,cacheUser);
        }
    }
}
