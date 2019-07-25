package com.tg.shop.core.domain.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信
 * @author Administrator
 */
@Data
@NoArgsConstructor
public class SmsInfo {

    /**
     * 签名
     */
    public static final String SIGN_NAME="糖果贝贝";

    /**
     * 注册码模板
     */
    public static final String TEMPLATE_REGISTER_CODE = "SMS_151905513";

}
