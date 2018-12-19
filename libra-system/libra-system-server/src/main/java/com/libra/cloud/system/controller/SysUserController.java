package com.libra.cloud.system.controller;

import com.libra.cloud.system.api.context.LoginContext;
import com.libra.cloud.system.api.entity.SysUser;
import com.libra.cloud.system.service.SysUserService;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import com.libra.scanner.annotation.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@RestController
@ApiResource(name = "系统用户管理", path = "/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @ApiResource(name = "用户登录", path = "/login", requiredPermission = false, requiredLogin = false)
    public ResponseData login(RequestData requestData) {
        String account = requestData.getString("account");
        String password = requestData.getString("password");
        String token = sysUserService.login(account, password);
        return ResponseData.success(token);
    }

    @ApiResource(name = "用户登出", path = "/logout")
    public ResponseData logout(RequestData requestData) {
        String token = requestData.getString("token");
        sysUserService.logout(token);
        return ResponseData.success();
    }

    @ApiResource(name = "获取用户信息", path = "/info")
    public ResponseData getUserInfo(RequestData requestData) {
        return ResponseData.success(LoginContext.me().getLoginUser());
    }

    @ApiResource(name = "添加用户", path = "/add")
    public ResponseData addUser(RequestData requestData) {
        SysUser sysUser = requestData.parse(SysUser.class);
        return ResponseData.success(sysUserService.addUser(sysUser));
    }

    @ApiResource(name = "更新用户", path = "/update")
    public ResponseData updateUser(RequestData requestData) {
        SysUser sysUser = requestData.parse(SysUser.class);
        return ResponseData.success(sysUserService.updateUser(sysUser));
    }

    @ApiResource(name = "删除用户", path = "/delete")
    public ResponseData deleteUser(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        sysUserService.deleteUser(userId);
        return ResponseData.success();
    }

    @ApiResource(name = "获取用户列表", path = "/list")
    public ResponseData getUserList(RequestData requestData) {
        return ResponseData.success(sysUserService.getUserList());
    }

    @ApiResource(name = "获取用户角色列表", path = "/roleList")
    public ResponseData getUserRoleList(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        return ResponseData.success(sysUserService.getUserRoleList(userId));
    }

    @ApiResource(name = "获取用户资源列表", path = "/resourceList")
    public ResponseData getUserResourceList(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        return ResponseData.success(sysUserService.getUserResourceList(userId));
    }

    @ApiResource(name = "添加用户某个角色", path = "/addRole")
    public ResponseData addUserRole(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        Integer roleId = requestData.getInteger("roleId");
        sysUserService.addUserRole(userId, roleId);
        return ResponseData.success();
    }

    @ApiResource(name = "删除用户某个角色", path = "/deleteRole")
    public ResponseData deleteUserRole(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        Integer roleId = requestData.getInteger("roleId");
        sysUserService.deleteUserRole(userId, roleId);
        return ResponseData.success();
    }

    @ApiResource(name = "删除用户所有角色", path = "/deleteAllRole")
    public ResponseData deleteUserAllRole(RequestData requestData) {
        Integer userId = requestData.getInteger("userId");
        sysUserService.deleteUserAllRole(userId);
        return ResponseData.success();
    }
}
