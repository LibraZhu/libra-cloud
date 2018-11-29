package com.libra.core.logger.config;

import com.libra.core.logger.config.properties.LogProperties;
import com.libra.core.logger.service.LogProducerService;
import com.libra.core.logger.service.impl.LogProducerServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.libra.core.constants.ConfigPrefixConstants.LOG_PREFIX;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 默认kafka消息队列日志
 */
@Configuration
public class LoggerAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = LOG_PREFIX)
    public LogProperties logProperties() {
        return new LogProperties();
    }

    @Bean
    @ConditionalOnProperty(prefix = LOG_PREFIX, value = "kafka", havingValue = "true")
    public LogProducerService logProducerService() {
        return new LogProducerServiceImpl();
    }
}
