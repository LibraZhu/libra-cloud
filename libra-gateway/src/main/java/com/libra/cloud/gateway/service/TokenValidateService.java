package com.libra.cloud.gateway.service;

import com.libra.cloud.gateway.exception.AuthExceptionEnum;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.config.properties.JwtProperties;
import com.libra.core.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import static com.libra.cloud.gateway.constants.AuthConstants.AUTH_HEADER;

/**
 * @author Libra
 * @date 2018/11/30
 * @description Token校验的服务
 */
public abstract class TokenValidateService {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * @author stylefeng
     * @Date 2018/8/13 22:11
     */
    public boolean doValidate(HttpServletRequest request) {

        //先获取token
        String tokenFromRequest = this.getTokenFromRequest(request);

        //校验token是否正确
        return this.validateToken(tokenFromRequest, request);
    }

    /**
     * 获取请求中的token
     *
     * @author stylefeng
     * @Date 2018/8/13 22:05
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        //获取token
        String authToken = request.getHeader(AUTH_HEADER);
        if (EmptyUtil.isEmpty(authToken)) {

            //如果header中没有token，则检查请求参数中是否带token
            authToken = request.getParameter("token");
            if (EmptyUtil.isEmpty(authToken)) {
                throw new ServiceException(AuthExceptionEnum.TOKEN_EMPTY);
            }
        } else {
            authToken = authToken.substring("Bearer ".length());
        }

        return authToken;
    }

    /**
     * 校验token
     *
     * @author stylefeng
     * @Date 2018/8/13 21:50
     */
    protected abstract boolean validateToken(String token, HttpServletRequest request);
}
