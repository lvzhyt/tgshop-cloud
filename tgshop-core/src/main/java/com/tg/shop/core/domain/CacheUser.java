package com.tg.shop.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CacheUser {

    private String token;

    private String userId;

    private String userName;

    private List<String> roles;

    private List<String> permission;


}
