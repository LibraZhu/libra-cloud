package com.libra.cloud.gateway.controller;

import com.libra.cloud.gateway.constants.AuthConstants;
import com.libra.cloud.gateway.consumer.AuthServiceConsumer;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 登录控制器
 */
@RestController
public class LoginController {
    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    /**
     * 登录接口
     */
    @RequestMapping(AuthConstants.AUTH_ACTION_URL)
    public ResponseData auth(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        String token = authServiceConsumer.login(userName, password);
        return ResponseData.success(token);
    }

    /**
     * 验证token是否正确
     */
    @RequestMapping(AuthConstants.VALIDATE_TOKEN_URL)
    public ResponseData validateToken(@RequestParam("token") String token) {
        boolean tokenFlag = authServiceConsumer.checkToken(token);
        return ResponseData.success(tokenFlag);
    }

    /**
     * 退出接口
     */
    @RequestMapping(AuthConstants.LOGOUT_URL)
    public ResponseData logout(@RequestParam("token") String token) {
        authServiceConsumer.logout(token);
        return ResponseData.success();
    }
}
