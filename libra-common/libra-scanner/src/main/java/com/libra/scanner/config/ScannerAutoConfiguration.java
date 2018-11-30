package com.libra.scanner.config;

import com.libra.scanner.ApiResourceScaner;
import com.libra.scanner.config.properties.ScannerProperties;
import com.libra.scanner.factory.ApiResourceFactory;
import com.libra.scanner.factory.DefaultApiResourceFactory;
import com.libra.scanner.listener.ResourceReportListener;
import com.libra.scanner.service.ResourceCollectService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.libra.core.constants.ConfigPrefixConstants.SCANNER_PREFIX;

/**
 * 扫描器默认配置
 * <p>
 * 注意：资源扫描的使用需要配置ResourceService这个Bean到Spring容器
 *
 * @author Libra
 * @date 2018/11/30
 * @description
 */
@Configuration
public class ScannerAutoConfiguration {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConfigurationProperties(prefix = SCANNER_PREFIX)
    public ScannerProperties scannerProperties() {
        return new ScannerProperties();
    }

    /**
     * 资源工厂
     */
    @Bean
    public ApiResourceFactory apiResourceFactory() {
        return new DefaultApiResourceFactory();
    }

    /**
     * 资源收集服务
     */
    @Bean
    public ResourceCollectService resourceCollectService(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ResourceCollectService(apiResourceFactory, scannerProperties);
    }

    /**
     * 资源扫描器
     */
    @Bean
    public ApiResourceScaner apiResourceScaner(ApiResourceFactory apiResourceFactory, ScannerProperties scannerProperties) {
        return new ApiResourceScaner(apiResourceFactory, scannerProperties, applicationName);
    }

    /**
     * 资源扫描之后的资源汇报操作（向libra-auth服务）
     */
    @Bean
    @ConditionalOnProperty(prefix = SCANNER_PREFIX, name = "open", havingValue = "true")
    public ResourceReportListener resourceReportListener() {
        return new ResourceReportListener();
    }
}
