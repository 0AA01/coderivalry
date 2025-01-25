package com.xzj.coderivalry.biz.userservice.common.constant;

/**
 * Redis常量类
 */
public class RedisConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "coderivalry:lock_user:register:%s";
}
