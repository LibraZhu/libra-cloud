package com.libra.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 系统管理
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.libra.cloud.gateway.consumer")
public class LibraGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraGatewayApplication.class, args);
    }
}
