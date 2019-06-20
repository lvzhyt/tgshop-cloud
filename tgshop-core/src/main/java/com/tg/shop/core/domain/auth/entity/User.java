package com.tg.shop.core.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;

    private String userName;

    private String avator;

    private String[] roles;


}
