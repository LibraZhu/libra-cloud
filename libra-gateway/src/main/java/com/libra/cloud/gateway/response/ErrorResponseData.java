package com.libra.cloud.gateway.response;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 请求失败的返回
 */
@Data
public class ErrorResponseData extends ResponseData {
    /**
     * 异常的具体类名称
     */
    private String exceptionClazz;

    public ErrorResponseData(String message) {
        super(false, ResponseData.DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }
}
