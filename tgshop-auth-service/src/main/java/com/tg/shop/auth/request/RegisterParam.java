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
    private String mobile;

    @NotEmpty
    @Length(min = 6,max = 20)
    private String password;

    /**
     * 验证码
     */
    @NotEmpty
    private String code;
}
