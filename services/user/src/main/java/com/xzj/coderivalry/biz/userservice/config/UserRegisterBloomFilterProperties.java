package com.xzj.coderivalry.biz.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用户注册布隆过滤器属性配置
 * 8.59MB
 * @author xzj
 */
@Data
@ConfigurationProperties(prefix = UserRegisterBloomFilterProperties.PREFIX)
public class UserRegisterBloomFilterProperties {

    public static final String PREFIX = "frameworks.cache.redis.bloom-filter.user-register";

    /**
     * 用户注册布隆过滤器实例名称
     */
    private String name = "xzj_user_register_cache_bloom_filter";

    /**
     * 每个元素的预期插入量
     */
    private Long expectedInsertions = 5000000L;

    /**
     * 预期错误概率
     */
    private Double falseProbability = 0.001;
}
