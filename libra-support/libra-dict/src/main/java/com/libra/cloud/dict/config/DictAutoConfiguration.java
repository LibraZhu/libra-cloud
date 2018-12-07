package com.libra.cloud.dict.config;

import com.libra.cloud.dict.controller.DictController;
import com.libra.cloud.dict.controller.DictTypeController;
import com.libra.cloud.dict.db.DictInitializer;
import com.libra.cloud.dict.db.DictTypeInitializer;
import com.libra.cloud.dict.provider.DictServiceProvider;
import com.libra.cloud.dict.provider.DictTypeServiceProvider;
import com.libra.cloud.dict.service.DictService;
import com.libra.cloud.dict.service.DictTypeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典的自动配置
 */
@Configuration
public class DictAutoConfiguration {

    /**
     * 数据库初始化
     */
    @Bean
    public DictInitializer dictInitializer() {
        return new DictInitializer();
    }

    @Bean
    public DictTypeInitializer dictTypeInitializer() {
        return new DictTypeInitializer();
    }

    /**
     * 控制器
     */
    @Bean
    public DictController dictController() {
        return new DictController();
    }

    @Bean
    public DictTypeController dictTypeController() {
        return new DictTypeController();
    }

    /**
     * 服务层
     */
    @Bean
    public DictService dictService() {
        return new DictService();
    }

    @Bean
    public DictTypeService dictTypeService() {
        return new DictTypeService();
    }

    /**
     * 服务提供者
     */
    @Bean
    public DictServiceProvider dictServiceProvider() {
        return new DictServiceProvider();
    }

    @Bean
    public DictTypeServiceProvider dictTypeServiceProvider() {
        return new DictTypeServiceProvider();
    }
}
