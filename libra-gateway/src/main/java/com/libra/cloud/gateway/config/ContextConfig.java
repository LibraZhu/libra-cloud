package com.libra.cloud.gateway.config;

import cn.hutool.core.bean.BeanUtil;
import com.libra.cloud.gateway.service.TokenValidateService;
import com.libra.cloud.gateway.service.impl.WithScannerTokenValidateServiceImpl;
import com.libra.core.exception.CoreExceptionEnum;
import com.libra.core.exception.ServiceException;
import com.libra.core.logger.aop.ChainOnConsumerAop;
import com.libra.core.logger.aop.ChainOnControllerAop;
import com.libra.core.logger.aop.ChainOnProviderAop;
import com.libra.core.reqres.response.ErrorResponseData;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 错误信息提示的配置
 */
@Configuration
public class ContextConfig {
    @Bean
    public TokenValidateService tokenValidateService() {
        return new WithScannerTokenValidateServiceImpl();
    }

    /**
     * zuul错误信息提示重写
     */
    @Bean
    public LibraErrorAttributes libraErrorAttributes() {
        return new LibraErrorAttributes();
    }

    /**
     * 调用链治理(调用远程服务之前的日志)
     */
    @Bean
    public ChainOnConsumerAop chainOnConsumerAop() {
        return new ChainOnConsumerAop();
    }

    /**
     * 调用链治理(控制器日志，和一些参数填充)
     */
    @Bean
    public ChainOnControllerAop chainOnControllerAop() {
        return new ChainOnControllerAop();
    }

    /**
     * 调用链治理(参数校验和错误日志的记录)
     */
    @Bean
    public ChainOnProviderAop chainOnProviderAop() {
        return new ChainOnProviderAop();
    }

    private class LibraErrorAttributes extends DefaultErrorAttributes {

        @Override
        public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
            RequestContext currentContext = RequestContext.getCurrentContext();
            Throwable throwable = currentContext.getThrowable();
            if (throwable instanceof ZuulException) {
                ZuulException zuulException = (ZuulException) throwable;
                Throwable cause = zuulException.getCause();
                if (cause instanceof ServiceException) {
                    ServiceException serviceException = (ServiceException) cause;
                    return BeanUtil.beanToMap(new ErrorResponseData(serviceException.getCode(), serviceException.getMessage(), null));
                }
            }

            return BeanUtil.beanToMap(new ErrorResponseData(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage(), null));
        }
    }
}
