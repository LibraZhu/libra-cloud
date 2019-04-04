package com.libra.cloud.poetry.config;

import com.libra.cloud.poetry.interceptor.TokenInterceptor;
import com.libra.cloud.system.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Libra
 * @date 2019/3/21
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/sysUser/**", "/role/**", "/resource/**", "/dict/**", "/api/file/**");
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/poetry/**");
    }


    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
}
