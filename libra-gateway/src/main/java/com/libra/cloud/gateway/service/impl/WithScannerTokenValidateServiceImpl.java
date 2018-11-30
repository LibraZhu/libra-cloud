package com.libra.cloud.gateway.service.impl;

import com.libra.cloud.gateway.consumer.AuthServiceConsumer;
import com.libra.cloud.gateway.consumer.ResourceServiceConsumer;
import com.libra.cloud.gateway.exception.AuthExceptionEnum;
import com.libra.cloud.gateway.service.TokenValidateService;
import com.libra.core.resouce.ResourceDefinition;
import com.libra.core.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 集成scanner的鉴权服务
 */
@Service
public class WithScannerTokenValidateServiceImpl extends TokenValidateService {
    @Autowired
    private ResourceServiceConsumer resourceServiceConsumer;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public boolean validateToken(String token, HttpServletRequest request) {

        String requestURI = null;
        if (request != null) {
            //获取context-path加servlet-path
            requestURI = request.getRequestURI();

            //如果是zuul开头的url，则去掉zuul再去资源校验
            if (requestURI.startsWith("/zuul")) {
                requestURI = requestURI.substring(5);
            }
        }

        //获取当前接口是否需要鉴权
        ResourceDefinition currentResource = resourceServiceConsumer.getResourceByUrl(requestURI);
        if (currentResource == null) {
            return true;
        }

        //判断是否需要登录
        if (currentResource.getRequiredLogin()) {

            //验证token是否正确
            boolean flag = authServiceConsumer.checkToken(token);
            if (flag) {
                return true;
            } else {
                throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
            }
        } else {
            return true;
        }
    }
}
