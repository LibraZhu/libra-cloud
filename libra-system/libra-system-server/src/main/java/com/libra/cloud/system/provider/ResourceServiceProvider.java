package com.libra.cloud.system.provider;

import com.libra.cloud.system.service.SysUserService;
import com.libra.core.api.ResourceService;
import com.libra.core.logger.util.LogUtil;
import com.libra.core.resouce.ResourceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 资源服务提供者
 */
@RestController
public class ResourceServiceProvider implements ResourceService {

    @Override
    public void reportResources(@RequestParam("appCode") String appCode,
                                @RequestBody Map<String, Map<String, ResourceDefinition>> resourceDefinitions) {
        LogUtil.info("上报资源");
    }

    @Override
    public Set<String> getUserResourceUrls(@RequestParam("accountId") String accountId) {
        return null;
    }

    @Override
    public ResourceDefinition getResourceByUrl(@RequestParam("url") String url) {
        return null;
    }
}
