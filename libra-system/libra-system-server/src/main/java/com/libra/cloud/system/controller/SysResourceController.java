package com.libra.cloud.system.controller;

import com.libra.cloud.system.service.SysResourceService;
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
@ApiResource(name = "资源管理", path = "/resource")
public class SysResourceController {
    @Autowired
    SysResourceService sysResourceService;

    @ApiResource(name = "获取资源列表", path = "/list")
    public ResponseData getResourceList(RequestData requestData) {
        return ResponseData.success(sysResourceService.getResourceList());
    }
}
