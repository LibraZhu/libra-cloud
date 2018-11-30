package com.libra.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 系统管理
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.libra.cloud.gateway.consumer")
@EnableZuulProxy
public class LibraGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraGatewayApplication.class, args);
    }
}
