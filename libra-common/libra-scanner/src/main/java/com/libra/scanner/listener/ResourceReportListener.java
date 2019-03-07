package com.libra.scanner.listener;

import com.libra.cloud.system.api.ResourceService;
import com.libra.cloud.system.api.entity.ResourceDefinition;
import com.libra.scanner.config.properties.ScannerProperties;
import com.libra.scanner.factory.ApiResourceFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.Map;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 监听项目初始化完毕, 报告到服务器资源
 */
public class ResourceReportListener implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("发送本系统的所有资源到libra-auth服务开始！");

        //获取当前系统的所有资源
        ApiResourceFactory resourceFactory = applicationContext.getBean(ApiResourceFactory.class);
        Map<String, Map<String, ResourceDefinition>> modularResources = resourceFactory.getModularResources();

        //发送资源到资源服务器
        ScannerProperties scannerProperties = applicationContext.getBean(ScannerProperties.class);
        ResourceService resourceService = applicationContext.getBean(ResourceService.class);
        resourceService.reportResources(scannerProperties.getAppCode(), modularResources);

        System.out.println("发送本系统的所有资源到libra-auth服务完毕！");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
