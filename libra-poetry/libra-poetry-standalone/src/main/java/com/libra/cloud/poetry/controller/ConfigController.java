package com.libra.cloud.poetry.controller;

import com.libra.cloud.system.entity.SysConfig;
import com.libra.cloud.system.service.SysConfigService;
import com.libra.core.annotation.IgnoreUserToken;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Libra
 * @date 2019/4/9
 * @description
 */
@RestController
@RequestMapping(name = "配置信息管理", path = "/poetry/config")
public class ConfigController {
    @Autowired
    SysConfigService sysConfigService;

    @IgnoreUserToken
    @PostMapping(name = "配置信息列表", path = "/list")
    public ResponseData getConfig(RequestData requestData) {
        Map<String, Object> map = new HashMap<>();
        List<SysConfig> list = sysConfigService.getConfigList();
        for (int i = 0; i < list.size(); i++) {
            SysConfig config = list.get(i);
            map.put(config.getName(), config.getValues());
        }
        return ResponseData.success(map);
    }
}
