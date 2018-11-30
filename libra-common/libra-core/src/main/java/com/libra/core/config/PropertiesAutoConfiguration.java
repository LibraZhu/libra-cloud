package com.libra.core.config;

import com.libra.core.config.properties.AppNameProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 默认的配置
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class PropertiesAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.application.name")
    public AppNameProperties appNameProperties() {
        return new AppNameProperties();
    }
}
