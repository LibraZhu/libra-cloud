package com.libra.cloud.gateway.config;

import com.libra.cloud.gateway.filter.AccessGatewayFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 配置
 */
@Configuration
public class FilterConfig {
    @Bean
    public AccessGatewayFilter accessGatewayFilter() {
        return new AccessGatewayFilter();
    }
}
