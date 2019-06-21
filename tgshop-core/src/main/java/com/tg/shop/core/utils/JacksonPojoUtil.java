package com.tg.shop.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class JacksonPojoUtil {

    /**
     * 将空字符串转为null
     * @param object
     */
    public static void convertEmptyStringToNull(Object object) {
        Assert.notNull(object, "object must not be null");
        try {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(object.getClass());
            for (PropertyDescriptor pd:propertyDescriptors){
                if(String.class.equals(pd.getPropertyType())){
                    Method writeMethod = pd.getWriteMethod();
                    Method readMethod = pd.getReadMethod();
                    if(readMethod!=null && writeMethod!=null){
                        String val = (String) readMethod.invoke(object);
                        if(val!=null && val.isEmpty()){
                            writeMethod.invoke(object,(String)null);
                        }
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
