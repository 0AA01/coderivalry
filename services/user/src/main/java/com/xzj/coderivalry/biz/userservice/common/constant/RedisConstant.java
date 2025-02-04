package com.xzj.coderivalry.biz.userservice.common.constant;

/**
 * Redis常量类
 */
public class RedisConstant {

    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "coderivalry:lock_user:register:%s";

    /**
     * 用户登录分布式锁
     */
    public static final String LOCK_USER_LOGIN_KEY = "coderivalry:lock_user:login:%s";

    /**
     * 用户查看竞赛分数分布式锁
     */
    public static final String LOCK_USER_COMPETITION_SCORE_KEY = "coderivalry:lock_user:competition:score:%s";

    /**
     * LUA查看用户竞赛排名
     */
    private static final String LUA_GET_USER_COMPETITION_RANKING_SCRIPT_PATH = "lua/getUserCompetitionRanking.lua";
}
