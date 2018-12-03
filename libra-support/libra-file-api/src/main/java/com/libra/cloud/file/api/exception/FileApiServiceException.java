package com.libra.cloud.file.api.exception;

import com.libra.core.exception.ApiServiceException;
import com.libra.core.exception.BaseExceptionEnum;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件远程调的业务异常
 */
public class FileApiServiceException extends ApiServiceException {
    public FileApiServiceException(BaseExceptionEnum exception) {
        super(exception);
    }

    @Override
    public String getExceptionClassName() {
        return FileApiServiceException.class.getName();
    }
}
