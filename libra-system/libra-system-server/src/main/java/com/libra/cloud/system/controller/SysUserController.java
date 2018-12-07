package com.libra.cloud.system.controller;

import com.libra.cloud.system.entity.SysUser;
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

    @ApiResource(name = "添加用户", path = "/add")
    public ResponseData addUser(RequestData requestData) {
        SysUser sysUser = requestData.parse(SysUser.class);
        sysUserService.addUser(sysUser);
        return ResponseData.success();
    }

    @ApiResource(name = "更新用户", path = "/update")
    public ResponseData updateUser(RequestData requestData) {
        SysUser sysUser = requestData.parse(SysUser.class);
        sysUserService.updateUser(sysUser);
        return ResponseData.success();
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
}
