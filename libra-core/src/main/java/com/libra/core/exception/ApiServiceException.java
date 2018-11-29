package com.libra.core.exception;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 远程接口调用出现的业务异常
 */
@Data
public abstract class ApiServiceException extends Exception {
    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误的提示信息
     */
    private String errorMessage;

    public ApiServiceException(BaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    /**
     * 获取异常的类的具体名称
     */
    public abstract String getExceptionClassName();
}
