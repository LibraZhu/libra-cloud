package com.libra.cloud.poetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 诗歌服务
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LibraPoetryCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraPoetryCloudApplication.class, args);
    }
}
