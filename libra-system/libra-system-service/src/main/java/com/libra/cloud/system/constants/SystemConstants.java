package com.libra.cloud.system.constants;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 系统管理常量
 */
public interface SystemConstants {
    /**
     * 登录用户缓存的前缀
     */
    String LOGIN_USER_CACHE_PREFIX = "LOGIN_SYS_";

    /**
     * 登录超时时间（单位：秒）
     */
    Long DEFAULT_LOGIN_TIME_OUT_SECS = 604800L;
}
