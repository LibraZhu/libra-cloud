package com.libra.cloud.poetry.exception;

import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 签名异常
 */
public enum PoetryExceptionEnum implements BaseExceptionEnum {
    TOKEN_ERROR(10001, "token无效"),
    SIGN_ERROR(10002, "签名错误"),
    USER_NOT_FOUND(10003, "用户不存在"),
    USER_EXIST(10004, "用户已存在"),
    INVALID_PWD(10005, "密码错误"),
    TEMPTY_POETRY(100010, "找不到诗文");

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
