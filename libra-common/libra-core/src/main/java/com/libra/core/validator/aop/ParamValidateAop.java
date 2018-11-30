package com.libra.core.validator.aop;

import com.libra.core.constants.AopSortConstants;
import com.libra.core.validator.util.CheckUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 参数校验的aop
 */
@Aspect
@Order(AopSortConstants.PARAM_VALIDATE_AOP_SORT)
public class ParamValidateAop {
    @Pointcut(value = "@annotation(com.libra.core.validator.ParamValidator)")
    private void cutService() {

    }

    @Around("cutService()")
    public Object doInvoke(ProceedingJoinPoint point) throws Throwable {

        //获取拦截方法的参数
        Object[] methodParams = point.getArgs();

        //如果请求参数为空，直接跳过aop
        if (methodParams == null || methodParams.length <= 0) {
            return point.proceed();
        } else {

            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            CheckUtil.validateParameters(methodParams);
            return point.proceed();
        }
    }
}
