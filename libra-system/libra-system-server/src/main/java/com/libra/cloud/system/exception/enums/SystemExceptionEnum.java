package com.libra.cloud.system.exception.enums;

import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 鉴权相关的错误异常
 */
public enum SystemExceptionEnum implements BaseExceptionEnum {
    USER_NOT_FOUND(3110, "用户不存在！"),
    USER_EXIST(3112, "用户已存在！"),
    ROLE_NOT_FOUND(3113, "角色已存在！"),
    ROLE_EXIST(3114, "角色已存在！"),

    INVALID_PWD(3111, "密码错误！");

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
