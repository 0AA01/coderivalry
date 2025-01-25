package com.xzj.coderivalry.framework.starter.database.config;

import com.xzj.coderivalry.framework.starter.database.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.Bean;

/**
 * dataBase自动配置文件
 */
public class DataBaseAutoConfiguration {

    /**
     * 元数据填充
     */
    @Bean
    public MyMetaObjectHandler myMetaObjectHandler() {
        return new MyMetaObjectHandler();
    }
}
