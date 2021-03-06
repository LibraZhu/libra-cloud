package com.libra.cloud.system.api;

import com.libra.cloud.system.api.entity.ResourceDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 系统管理服务端提供的远程服务
 */
@RequestMapping("/api/resource")
public interface ResourceService {
    /**
     * 报告业务系统的资源(Resources)到服务器,appCode若重复则会覆盖
     */
    @RequestMapping(value = "/reportResources", method = RequestMethod.POST)
    void reportResources(@RequestParam("appCode") String appCode, @RequestBody Map<String, Map<String, ResourceDefinition>> resourceDefinitions);

    /**
     * 获取用户所拥有的资源url
     */
    @RequestMapping(value = "/getUserResourceUrls", method = RequestMethod.POST)
    Set<String> getUserResourceUrls(@RequestParam("accountId") String accountId);

    /**
     * 获取资源通过url
     */
    @RequestMapping(value = "/getResourceByUrl", method = RequestMethod.POST)
    ResourceDefinition getResourceByUrl(@RequestParam("url") String url);
}
