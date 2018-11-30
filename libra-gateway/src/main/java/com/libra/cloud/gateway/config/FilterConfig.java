package com.libra.cloud.gateway.config;

import com.libra.cloud.gateway.filter.JwtTokenFilter;
import com.libra.cloud.gateway.filter.RequestNoGenerateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 过滤器的配置
 */
@Configuration
public class FilterConfig {
    /**
     * token过滤器，检查每次请求token是否合法
     */
    @Bean
    public JwtTokenFilter authFilter() {
        return new JwtTokenFilter();
    }

    /**
     * 资源过滤器，检查每次请求是否有权限访问某些资源
     */
    //@Bean
    //public PathMatchFilter pathMatchFilter() {
    //    return new PathMatchFilter();
    //}

    /**
     * 请求唯一编号生成器，每次请求入网关时都会生成一个唯一编号，用来记录一次请求的所有日志和异常信息
     */
    @Bean
    public RequestNoGenerateFilter requestNoGenerateFilter() {
        return new RequestNoGenerateFilter();
    }
}
