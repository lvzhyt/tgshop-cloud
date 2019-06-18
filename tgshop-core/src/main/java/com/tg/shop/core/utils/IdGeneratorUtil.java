package com.tg.shop.core.utils;

import com.tg.shop.core.generator.IdGenerator;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @Author: tg
 * @Date: 2019/1/8 12:05
 */
public class IdGeneratorUtil {

    private static IdGenerator idGenerator = null;

    public static String nextId(){
        if(idGenerator==null){
           idGenerator =  SpringContextUtil.getApplicationContext().getBean(IdGenerator.class);
        }
        Assert.notNull(idGenerator,"IdGenerator is not injected");
        return idGenerator.nextStringId();
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
