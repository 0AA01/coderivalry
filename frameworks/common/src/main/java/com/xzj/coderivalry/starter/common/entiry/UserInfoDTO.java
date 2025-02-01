package com.xzj.coderivalry.starter.common.entiry;

import lombok.Data;

/**
 * 用户信息实体
 */
@Data
public class UserInfoDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户Token
     */
    private String token;
}
