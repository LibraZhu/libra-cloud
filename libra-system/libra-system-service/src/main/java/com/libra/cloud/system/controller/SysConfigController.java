package com.libra.cloud.system.controller;

import com.libra.cloud.system.service.SysConfigService;
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
@ApiResource(name = "配置信息管理", path = "/config")
public class SysConfigController {
    @Autowired
    SysConfigService sysConfigService;

    @ApiResource(name = "获取配置信息", path = "/list")
    public ResponseData getConfigeList(RequestData requestData) {
        return ResponseData.success(sysConfigService.getConfigList());
    }
}
