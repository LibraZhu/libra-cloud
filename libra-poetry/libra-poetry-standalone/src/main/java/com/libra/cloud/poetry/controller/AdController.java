package com.libra.cloud.poetry.controller;

import com.libra.cloud.system.service.SysAdService;
import com.libra.core.annotation.IgnoreUserToken;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Libra
 * @date 2019/4/9
 * @description
 */
@RestController
@RequestMapping(name = "广告信息管理", path = "/poetry/ad")
public class AdController {
    @Autowired
    SysAdService sysAdService;

    @IgnoreUserToken
    @PostMapping(name = "Banner信息列表", path = "/banner")
    public ResponseData getConfig(RequestData requestData) {
        return ResponseData.success(sysAdService.selectAdForBanner());
    }
}
