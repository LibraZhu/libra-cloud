package com.libra.cloud.system.controller;

import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRole;
import com.libra.cloud.system.service.SysResourceService;
import com.libra.cloud.system.service.SysRoleResourceService;
import com.libra.cloud.system.service.SysRoleService;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import com.libra.scanner.annotation.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@RestController
@ApiResource(name = "角色管理", path = "/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysResourceService sysResourceService;
    @Autowired
    SysRoleResourceService sysRoleResourceService;

    @ApiResource(name = "添加角色", path = "/add")
    public ResponseData addRole(RequestData requestData) {
        SysRole sysRole = requestData.parse(SysRole.class);
        return ResponseData.success(sysRoleService.addRole(sysRole));
    }

    @ApiResource(name = "更新角色", path = "/update")
    public ResponseData updateRole(RequestData requestData) {
        SysRole sysRole = requestData.parse(SysRole.class);
        return ResponseData.success(sysRoleService.updateRole(sysRole));
    }

    @ApiResource(name = "删除角色", path = "/delete")
    public ResponseData deleteRole(RequestData requestData) {
        Integer RoleId = requestData.getInteger("roleId");
        sysRoleService.deleteRole(RoleId);
        return ResponseData.success();
    }

    @ApiResource(name = "获取角色列表", path = "/list")
    public ResponseData getRoleList(RequestData requestData) {
        return ResponseData.success(sysRoleService.getRoleList());
    }

    @ApiResource(name = "获取角色资源列表", path = "/resourceList")
    public ResponseData getRoleResourceList(RequestData requestData) {
        Integer roleId = requestData.getInteger("roleId");
        return ResponseData.success(sysResourceService.getRoleResourceList(roleId));
    }

    @ApiResource(name = "修改角色资源列表", path = "/modifyResourceList")
    public ResponseData modifyRoleResourceList(RequestData requestData) {
        Integer roleId = requestData.getInteger("roleId");
        List<String> resourceList = requestData.getList("resourceList", String.class);
        sysRoleResourceService.insertOrUpdateRoleResource(roleId, resourceList);
        return ResponseData.success();
    }
}
