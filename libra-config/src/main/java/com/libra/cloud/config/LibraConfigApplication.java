package com.libra.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类
 *
 * <pre>
 * 获取git上的配置信息的访问地址:
 *      /{application}/{profile}/{label}
 *      /{application}-{profile}.yml
 *      /{label}/{application}-{profile}.yml
 *      /{application}-{profile}.properties
 *      /{label}/{application}-{profile}.properties
 * </pre>
 *
 * @author Libra
 * @date 2018/11/28
 * @description
 */
@SpringBootApplication
@EnableConfigServer
public class LibraConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraConfigApplication.class, args);
    }
}
