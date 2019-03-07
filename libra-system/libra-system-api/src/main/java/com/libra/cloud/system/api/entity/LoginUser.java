package com.libra.cloud.system.api.entity;

import com.libra.interfaces.user.AbstractLoginUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 当前用户的登录信息
 */
@SuppressWarnings("ALL")
@Data
public class LoginUser implements AbstractLoginUser, Serializable {
    /**
     * 账号id
     */
    private Integer userId;
    /**
     * 账号
     */
    private SysUser user;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 角色id集合
     */
    private Set<Integer> roleIds;
    /**
     * 角色code集合
     */
    private Set<String> roleCodes;

    /**
     * 可用资源集合
     */
    private Set<String> resourceUrls;

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public SysUser getUser() {
        return user;
    }

    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    @Override
    public Set<String> getRoleCodes() {
        return roleCodes;
    }

    @Override
    public Set<String> getResourceUrls() {
        return resourceUrls;
    }
}
