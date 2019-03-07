package com.libra.cloud.gateway.config;


import cn.hutool.core.date.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author Libra
 * @date 2018/11/28
 * @description
 */
@Configuration
public class WebAutoConfiguration extends WebFluxConfigurationSupport {
    /**
     * 时间转化器
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) super.requestMappingHandlerAdapter().getWebBindingInitializer();
        if ((initializer != null ? initializer.getConversionService() : null) != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    public class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String dateString) {
            return DateUtil.parse(dateString);
        }
    }
}
