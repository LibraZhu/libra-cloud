package com.libra.cloud.poetry.controller;

import com.libra.cloud.poetry.service.UserService;
import com.libra.core.constants.Constants;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@RestController
@RequestMapping(name = "用户管理", path = "/poetry/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(name = "用户登录", path = "/login", method = RequestMethod.POST)
    public ResponseData login(RequestData requestData) {
        String account = requestData.getString("account");
        String password = requestData.getString("password");
        return ResponseData.success(userService.login(account, password));
    }

    @RequestMapping(name = "用户登出", path = "/logout")
    public ResponseData logout(RequestData requestData) {
        userService.logout();
        return ResponseData.success();
    }

    @RequestMapping(name = "获取用户信息", path = "/info")
    public ResponseData getUserInfo(RequestData requestData) {
        return ResponseData.success(userService.getLoginUserByToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
    }
}
