package com.xzj.coderivalry.biz.userservice.vo;

import lombok.Data;

/**
 * 用户注册展示参数
 * @author xzj
 */
@Data
public class UserLoginVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * Token
     */
    private String token;
}
