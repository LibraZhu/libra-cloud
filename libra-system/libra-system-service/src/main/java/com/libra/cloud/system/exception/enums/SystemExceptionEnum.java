package com.libra.cloud.system.exception.enums;

import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 鉴权相关的错误异常
 */
public enum SystemExceptionEnum implements BaseExceptionEnum {
    TOKEN_ERROR(10001, "token无效"),
    NO_PERMISSION(10002, "没有访问该资源的权限"),
    USER_NOT_FOUND(10003, "用户不存在"),
    USER_EXIST(10004, "用户已存在"),
    ROLE_NOT_FOUND(10005, "角色已存在"),
    ROLE_EXIST(10006, "角色已存在"),
    INVALID_PWD(10007, "密码错误");
    private int code;
    private String message;

    SystemExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
