package com.libra.cloud.file.exception;

import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件异常枚举
 */
public enum FileExceptionEnum implements BaseExceptionEnum {
    IO_ERROR(3001, "文件流错误，请检查网络是否连通！"),
    FILE_NOT_FOUND(3002, "文件未找到!"),
    FILE_NAME_FORMAT_ERROR(3003, "文件名称转化错误，文件名不合法！"),
    FILE_NAME_ERROR(3004, "文件id格式错误！");

    FileExceptionEnum(int code, String message) {
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
