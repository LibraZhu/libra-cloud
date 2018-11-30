package com.libra.core.exception;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 异常规范
 */
public interface BaseExceptionEnum {
    /**
     * 获取异常的状态码
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     */
    String getMessage();
}
