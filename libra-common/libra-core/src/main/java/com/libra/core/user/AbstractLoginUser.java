package com.libra.core.user;

import java.io.Serializable;
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
     * 获取用户id
     */
    <T> T getUserId();

    /**
     * 获取用户
     */
    <T> T getUser();

    /**
     * 获取用户唯一id
     */
    <T> T getAppId();

    /**
     * 获取角色id的集合
     */
    <T> Set<T> getRoleIds();

    /**
     * 获取角色code的集合
     */
    <T> Set<T> getRoleCodes();

    /**
     * 包含的资源权限url
     */
    <T> Set<T> getResourceUrls();
}
