package com.libra.cloud.gateway.service;

import com.libra.core.util.HttpContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/30
 * @description Token校验的服务
 */
public abstract class TokenValidateService {
    /**
     * @author stylefeng
     * @Date 2018/8/13 22:11
     */
    public boolean doValidate(HttpServletRequest request) {

        //先获取token
        String tokenFromRequest = HttpContext.getToken();

        //校验token是否正确
        return this.validateToken(tokenFromRequest, request);
    }

    /**
     * 校验token
     *
     * @author stylefeng
     * @Date 2018/8/13 21:50
     */
    protected abstract boolean validateToken(String token, HttpServletRequest request);
}
