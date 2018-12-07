package com.libra.cloud.system.provider;

import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.service.SysResourceService;
import com.libra.core.api.ResourceService;
import com.libra.core.logger.util.LogUtil;
import com.libra.core.resouce.ResourceDefinition;
import com.libra.scanner.factory.ApiResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 资源服务提供者
 */
@RestController
public class ResourceServiceProvider implements ResourceService {
    @Autowired
    ApiResourceFactory apiResourceFactory;
    @Autowired
    SysResourceService sysResourceService;

    @Override
    public void reportResources(@RequestParam("appCode") String appCode,
                                @RequestBody Map<String, Map<String, ResourceDefinition>> resourceDefinitions) {
        LogUtil.info("上报资源");
        List<SysResource> resourceList = new ArrayList<>();
        for (Map<String, ResourceDefinition> map : resourceDefinitions.values()) {
            for (Map.Entry<String, ResourceDefinition> entry : map.entrySet()) {
                SysResource sysResource = new SysResource();
                sysResource.setAppCode(entry.getValue().getAppCode());
                sysResource.setClassName(entry.getValue().getClassName());
                sysResource.setCode(entry.getValue().getCode());
                sysResource.setCreateTime(entry.getValue().getCreateTime());
                sysResource.setHttpMethod(entry.getValue().getHttpMethod());
                sysResource.setIpAddress(entry.getValue().getIpAddress());
                sysResource.setMenuFlag(entry.getValue().getMenuFlag() ? 1 : 0);
                sysResource.setMethodName(entry.getValue().getMethodName());
                sysResource.setClassName(entry.getValue().getClassName());
                sysResource.setModularCode(entry.getValue().getModularCode());
                sysResource.setModularName(entry.getValue().getModularName());
                sysResource.setName(entry.getValue().getName());
                sysResource.setRequiredLogin(entry.getValue().getRequiredLogin() ? 1 : 0);
                sysResource.setRequiredPermission(entry.getValue().getRequiredPermission() ? 1 : 0);
                sysResource.setUpdateTime(new Date());
                sysResource.setUrl(entry.getValue().getUrl());
                resourceList.add(sysResource);
            }
        }
        sysResourceService.insertOrUpdateBatch(resourceList);
    }

    @Override
    public Set<String> getUserResourceUrls(@RequestParam("accountId") String accountId) {
        return null;
    }

    @Override
    public ResourceDefinition getResourceByUrl(@RequestParam("url") String url) {
        return apiResourceFactory.getResourceByUrl(url);
    }
}
