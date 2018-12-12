package com.libra.cloud.gateway.constants;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 鉴权相关的常量
 */
public interface AuthConstants {

    /**
     * 鉴权地址
     */
    String AUTH_ACTION_URL = "/user/login";

    /**
     * 检验token是否正确
     */
    String VALIDATE_TOKEN_URL = "/api/auth/checkToken";

}
