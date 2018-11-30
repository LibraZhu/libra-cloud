package com.libra.core.constants;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 框架通用常量
 */
public interface Constants {
    /**
     * 请求号header标识
     */
    String REQUEST_NO_HEADER_NAME = "Request-No";

    /**
     * header中的spanId，传递规则：request header中传递本服务的id
     */
    String SPAN_ID_HEADER_NAME = "Span-Id";
}
