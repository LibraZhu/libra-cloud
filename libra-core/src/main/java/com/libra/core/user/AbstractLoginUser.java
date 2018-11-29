package com.libra.core.user;

import java.util.Set;

/**
 * 登录中的用户信息
 * <p>
 * 为何类型是泛型，因为具体的项目不知道类型是什么
 *
 * @author Libra
 * @date 2018/11/28
 * @description
 */
public interface AbstractLoginUser {
    /**
     * 获取用户唯一id
     */
    <T> T getUserUniqueId();

    /**
     * 获取用户唯一id
     */
    <T> T getAppId();

    /**
     * 获取角色id的集合
     */
    <T> Set<T> getRoleIds();

    /**
     * 角色编码集合
     */
    <T> Set<T> getRoleCodes();

    /**
     * 包含的资源权限url
     */
    <T> Set<T> getResourceUrls();
}
