package com.libra.cloud.poetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 诗歌服务
 */
@SpringBootApplication
@ComponentScan({"com.libra.cloud"})
@ServletComponentScan
public class LibraPoetryStandaloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraPoetryStandaloneApplication.class, args);
    }
}
