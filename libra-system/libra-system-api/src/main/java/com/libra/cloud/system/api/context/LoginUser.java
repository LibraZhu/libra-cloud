package com.libra.cloud.system.api.context;

import com.libra.core.user.AbstractLoginUser;
import lombok.Data;

import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 当前用户的登录信息
 */
@SuppressWarnings("ALL")
@Data
public class LoginUser implements AbstractLoginUser {
    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 角色id集合
     */
    private Set<Long> roleIds;

    /**
     * 角色编码集合
     */
    private Set<Long> roleCodes;

    /**
     * 可用资源集合
     */
    private Set<Long> resourceUrls;

    @Override
    public Long getUserUniqueId() {
        return accountId;
    }

    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public Set<Long> getRoleIds() {
        return roleIds;
    }

    @Override
    public Set<Long> getRoleCodes() {
        return roleCodes;
    }

    @Override
    public Set<Long> getResourceUrls() {
        return resourceUrls;
    }
}
