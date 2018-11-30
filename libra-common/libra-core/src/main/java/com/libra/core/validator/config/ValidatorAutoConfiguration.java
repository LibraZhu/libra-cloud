package com.libra.core.validator.config;

import com.libra.core.validator.aop.ParamValidateAop;
import org.springframework.context.annotation.Bean;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 签名的自动配置
 */
public class ValidatorAutoConfiguration {
    @Bean
    public ParamValidateAop paramValidateAop() {
        return new ParamValidateAop();
    }
}
