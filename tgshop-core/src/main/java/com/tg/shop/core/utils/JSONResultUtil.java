package com.tg.shop.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.tg.shop.core.domain.ResultState;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @Author: tg
 * @Date: 2019/3/19 8:49
 */
public class JSONResultUtil {

    private static final String DEFAULT_SUCCESS_MESSAGE="操作成功";

    public static JSONObject createJsonObject(){
        return createJsonObject(new ResultState());
    }

    public static JSONObject createJsonObject(Object data){
        return createJsonObject(new ResultState(data));
    }

    public static JSONObject createJsonObject(String errorCode){
        return createJsonObject(new ResultState(ResultState.MESSAGE_TYPE_ERROR,errorCode));
    }

    public static JSONObject createJsonObject(String errorCode, List<FieldError> errors){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",0);
        jsonObject.put("errorCode",errorCode);
        jsonObject.put("errorMessage",errorCode);
        jsonObject.put("errors",errors);
        return jsonObject;
    }

    /**
     * 创建返回信息
     * @param resultState
     * @return
     */
    public static JSONObject createJsonObject(ResultState resultState) {
        JSONObject jsonObject = new JSONObject();
        if(resultState==null){
            jsonObject.put("result",1);
            jsonObject.put("successMessage",ResultState.DEFAULT_SUCCESS_MESSAGE);
            return jsonObject;
        }
        int result = (resultState.getErrorCode()==null || ResultState.STATE_OK.equals(resultState.getErrorCode()))? 1 : 0;
        jsonObject.put("result",result);
        jsonObject.put("successMessage",resultState.getSuccessMessage());
        jsonObject.put("errorCode",resultState.getErrorCode());
        jsonObject.put("errorMessage",resultState.getErrorMessage());
        jsonObject.put("data",resultState.getData());

        return jsonObject;
    }

    public static JSONObject createJsonObject(ErrorCodeFeature errorCodeFeature) {
        return createJsonObject(errorCodeFeature,null);
    }

    public static JSONObject createJsonObject(ErrorCodeFeature errorCodeFeature, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",0);
        jsonObject.put("errorCode",errorCodeFeature.getCode());
        jsonObject.put("errorMessage",errorCodeFeature.getValue());
        jsonObject.put("data",data);

        return jsonObject;
    }

}
