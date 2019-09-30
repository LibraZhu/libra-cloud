package com.libra.cloud.poetry.controller;

import com.libra.cloud.poetry.service.FollowService;
import com.libra.cloud.poetry.service.UserService;
import com.libra.core.annotation.IgnoreUserToken;
import com.libra.core.constants.Constants;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;
    @Autowired
    FollowService followService;

    @IgnoreUserToken
    @PostMapping(name = "用户登录", path = "/login")
    public ResponseData login(RequestData requestData) {
        String account = requestData.getParam().getString("account");
        String password = requestData.getParam().getString("password");
        return ResponseData.success(userService.login(account, password));
    }

    @PostMapping(name = "用户登出", path = "/logout")
    public ResponseData logout(RequestData requestData) {
        userService.logout();
        return ResponseData.success();
    }

    @PostMapping(name = "获取用户信息", path = "/info")
    public ResponseData getUserInfo(RequestData requestData) {
        String token = requestData.getString(Constants.REQUEST_TOKEN_PARAM);
        Integer userId = Integer.parseInt(jwtTokenUtil.getUserIdFromToken(token));
        return ResponseData.success(userService.getUserInfo(token, userId));
    }

    @PostMapping(name = "获取用户信息", path = "/detail")
    public ResponseData getUserDetail(RequestData requestData) {
        Integer userId = requestData.getParam().getInteger("userId");
        return ResponseData.success(userService.getUserInfo(null, userId));
    }

    @IgnoreUserToken
    @PostMapping(name = "用户登录", path = "/wx/login")
    public ResponseData wxLogin(RequestData requestData) {
        String code = requestData.getParam().getString("code");
        String nickName = requestData.getParam().getString("nickName");
        String avatarUrl = requestData.getParam().getString("avatarUrl");
        String province = requestData.getParam().getString("province");
        String city = requestData.getParam().getString("city");
        return ResponseData.success(userService.wxLogin(code, nickName, avatarUrl, province, city));
    }

    @PostMapping(name = "获取用户关注列表", path = "/follow/list")
    public ResponseData getFollowList(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        return ResponseData.success(followService.selectFollowPage(userId));
    }

    @PostMapping(name = "获取用户粉丝列表", path = "/fans/list")
    public ResponseData getFansList(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        return ResponseData.success(followService.selectFansPage(userId));
    }

    @PostMapping(name = "获取关注状态", path = "/follow/status")
    public ResponseData getFollowStatus(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer followId = requestData.getParam().getInteger("followId");
        return ResponseData.success(followService.isFollow(userId, followId));
    }

    @PostMapping(name = "关注用户", path = "/follow/action")
    public ResponseData follow(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer followId = requestData.getParam().getInteger("followId");
        Integer status = requestData.getParam().getInteger("status");
        return ResponseData.success(followService.follow(userId, followId, status));
    }
}
