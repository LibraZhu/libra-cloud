package com.libra.cloud.system;

import com.libra.core.logger.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 系统管理
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class LibraSystemServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraSystemServerApplication.class, args);
        LogUtil.info("system启动成功！");
    }
}
