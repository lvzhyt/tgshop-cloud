package com.tg.shop.core.annotation;


import java.lang.annotation.*;

/**
 * 用户token认证
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AuthToken
@Inherited
public @interface UserToken {
    boolean required() default true;
}
