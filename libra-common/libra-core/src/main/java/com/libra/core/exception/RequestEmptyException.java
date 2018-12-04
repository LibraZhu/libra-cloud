package com.libra.core.exception;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 业务异常的封装
 */
public class RequestEmptyException extends ServiceException {
    public RequestEmptyException() {
        super(400, "请求数据不完整或格式错误！");
    }

    public RequestEmptyException(String errorMessage) {
        super(400, errorMessage);
    }

    /**
     * 不拷贝栈信息，提高性能
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
