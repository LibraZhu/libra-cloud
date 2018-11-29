package com.libra.core.context;

import com.libra.core.base.api.AbstractBaseRequest;
import com.libra.core.constants.Constants;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.HttpContext;
import com.libra.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 获取当前请求的请求号，没有请求号则生成空串
 */
@Slf4j
public class RequestNoContext {
    public static String getRequestNoByHttpHeader() {
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.info("获取请求号失败，当前不是http请求环境！");
            }
            return "";
        } else {
            String requestNo = request.getHeader(Constants.REQUEST_NO_HEADER_NAME);
            if (EmptyUtil.isEmpty(requestNo)) {
                return "";
            } else {
                return requestNo;
            }
        }
    }

    /**
     * 通过请求参数获取requestNo，参数必须是AbstractBaseRequest的子类
     */
    public static String getRequestNoByRequestParam(Object[] params) {

        if (params == null || params.length <= 0) {
            return "";
        } else {
            for (Object paramItem : params) {
                if (paramItem instanceof AbstractBaseRequest) {
                    AbstractBaseRequest abstractBaseRequest = (AbstractBaseRequest) paramItem;
                    return abstractBaseRequest.getRequestNo();
                }
            }
            return "";
        }
    }
}
