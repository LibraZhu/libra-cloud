package com.libra.cloud.poetry.config;

import com.libra.core.logger.aop.ChainOnConsumerAop;
import com.libra.core.logger.aop.ChainOnControllerAop;
import com.libra.core.logger.aop.ChainOnProviderAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 全局配置
 */
@Configuration
public class ContextConfig {

    /**
     * 调用链治理
     */
    @Bean
    public ChainOnConsumerAop chainOnConsumerAop() {
        return new ChainOnConsumerAop();
    }

    /**
     * 调用链治理
     */
    @Bean
    public ChainOnControllerAop chainOnControllerAop() {
        return new ChainOnControllerAop();
    }

    /**
     * 调用链治理
     */
    @Bean
    public ChainOnProviderAop chainOnProviderAop() {
        return new ChainOnProviderAop();
    }
}
