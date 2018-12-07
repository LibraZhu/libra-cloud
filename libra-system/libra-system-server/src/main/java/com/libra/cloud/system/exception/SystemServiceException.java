package com.libra.cloud.system.exception;

import com.libra.core.exception.ApiServiceException;
import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 系统管理服务抛出的异常
 */
public class SystemServiceException extends ApiServiceException {
    public SystemServiceException(BaseExceptionEnum exception) {
        super(exception);
    }

    @Override
    public String getExceptionClassName() {
        return SystemServiceException.class.getName();
    }
}
