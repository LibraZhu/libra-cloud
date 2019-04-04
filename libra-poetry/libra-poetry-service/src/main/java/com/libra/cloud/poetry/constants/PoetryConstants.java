package com.libra.cloud.poetry.constants;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 常量
 */
public interface PoetryConstants {
    /**
     * 登录用户缓存的前缀
     */
    String LOGIN_USER_CACHE_PREFIX = "LOGIN_";

    /**
     * 登录超时时间（单位：秒）
     */
    Long DEFAULT_LOGIN_TIME_OUT_SECS = 604800L;
    /**
     * 每页数量
     */
    Integer PAGESIZE = 20;
}
