package com.tg.shop.core.exception.handler;

import com.tg.shop.core.entity.ErrorCode;
import com.tg.shop.core.entity.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: tg
 * @Date: 2019/3/19 9:14
 */
@Slf4j
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResultObject processException(Exception ex){
        log.error("GlobalDefaultExceptionHandler "+ex.getMessage(),ex);
        ResultObject resultObject = new ResultObject<>(ErrorCode.SERVER_ERROR,ex.getMessage());
        return resultObject;
    }
}
