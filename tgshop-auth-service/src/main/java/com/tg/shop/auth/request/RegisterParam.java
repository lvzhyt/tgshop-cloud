package com.tg.shop.auth.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author Administrator
 */
@Data
public class RegisterParam {

    @NotEmpty
    private String userName;

    @NotEmpty
    @Length(min = 6,max = 10)
    private String password;

    /**
     * 验证码
     */
    @NotEmpty
    private String code;
}
