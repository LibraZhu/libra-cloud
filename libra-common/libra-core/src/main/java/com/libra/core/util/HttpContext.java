package com.libra.core.util;

import com.libra.core.constants.Constants;
import com.libra.core.exception.ServiceException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 快捷获取HttpServletRequest, HttpServletResponse
 */
public class HttpContext {
    /**
     * 获取请求的ip地址
     */
    public static String getIp() {
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            return "127.0.0.1";
        } else {
            return request.getRemoteHost();
        }
    }

    /**
     * 获取当前请求的Request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的Response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        } else {
            return requestAttributes.getResponse();
        }
    }

    /**
     * 获取所有请求的值
     */
    public static Map<String, String> getRequestParameters() {
        HashMap<String, String> values = new HashMap<>();
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            return values;
        }
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paramName = (String) enums.nextElement();
            String paramValue = request.getParameter(paramName);
            values.put(paramName, paramValue);
        }
        return values;
    }

    /**
     * 获取token
     *
     * @return 获取token
     */
    public static String getToken() {
        HttpServletRequest request = getRequest();
        String authToken = request.getHeader(Constants.REQUEST_AUTH_HEADER);
        if (EmptyUtil.isEmpty(authToken)) {
            //如果header中没有token，则检查请求参数中是否带token
            authToken = request.getParameter("token");
        } else {
            if (authToken.startsWith("Bearer ")) {
                authToken = authToken.substring("Bearer ".length());
            }
        }
        return authToken;
    }
}
