package com.libra.core.config;

import com.libra.core.db.listener.InitTableListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 数据库初始化默认配置
 */
@Configuration
public class DbInitializerAutoConfiguration {
    @Bean
    public InitTableListener initTableListener() {
        return new InitTableListener();
    }
}
