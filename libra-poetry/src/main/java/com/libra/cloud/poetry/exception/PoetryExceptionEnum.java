package com.libra.cloud.poetry.exception;

import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 签名异常
 */
public enum PoetryExceptionEnum implements BaseExceptionEnum {
    TEMPTY_POETRY(100001, "找不到诗文"),
    NO_PERMISSION(8003, "没有访问该资源的权限");

    PoetryExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
