package com.libra.cloud.gateway.service.impl;

import com.libra.cloud.gateway.exception.AuthExceptionEnum;
import com.libra.cloud.gateway.service.TokenValidateService;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 纯token验证鉴权服
 */
@Service
public class PureTokenValidateServiceImpl extends TokenValidateService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean validateToken(String token, HttpServletRequest request) {

        try {
            boolean flag = jwtTokenUtil.isTokenExpired(token);
            if (flag) {
                throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new ServiceException(AuthExceptionEnum.TOKEN_ERROR);
        }
    }
}
