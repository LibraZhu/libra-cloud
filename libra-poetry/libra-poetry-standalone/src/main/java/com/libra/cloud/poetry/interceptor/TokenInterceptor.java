package com.libra.cloud.poetry.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.libra.cloud.poetry.exception.PoetryExceptionEnum;
import com.libra.cloud.poetry.service.UserService;
import com.libra.cloud.poetry.wrapper.RequestWrapper;
import com.libra.core.constants.Constants;
import com.libra.core.exception.ServiceException;
import com.libra.core.util.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Libra
 * @date 2019/3/21
 * @description
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUri = null;
        if (request != null) {
            requestUri = request.getRequestURI();
            String authToken = null;
            if (request instanceof RequestWrapper) {
                RequestWrapper requestWrapper = (RequestWrapper) request;
                JSONObject body = JSON.parseObject(requestWrapper.getBody());
                checkSign(body);
                try {
                    authToken = body.getString(Constants.REQUEST_TOKEN_PARAM);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!requestUri.equals("/poetry/user/login")) {
                if (authToken == null) {
                    authToken = HttpContext.getToken();
                }
                if (authToken == null) {
                    throw new ServiceException(PoetryExceptionEnum.TOKEN_ERROR);
                }
                //验证token是否正确
                boolean check = userService.checkToken(authToken);
                if (!check) {
                    throw new ServiceException(PoetryExceptionEnum.TOKEN_ERROR);
                }
            }
        }
        return true;
    }

    private boolean checkSign(JSONObject body) {
        String sign = "";
        TreeMap<String, Object> map = new TreeMap<>();
        for (Map.Entry<String, Object> entry : body.entrySet()) {
            String key = entry.getKey();
            if (!"sign".equals(key)) {
                map.put(key, entry.getValue());
            } else {
                sign = entry.getValue().toString();
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                sb.append(key).append("=").append("&");
            } else if (value instanceof JSONObject) {
                sb.append(key).append("=");
                for (Map.Entry<String, Object> e : ((JSONObject) value).entrySet()) {
                    sb.append(e.getKey()).append("=").append(JSON.toJSON(e.getValue())).append("&");
                }

            } else {
                sb.append(key).append("=").append(JSON.toJSON(value)).append("&");
            }
        }

        if (sign.isEmpty() || !sign.equals(Base64Utils.encodeToString(sb.substring(0, sb.length() - 1).getBytes()))) {
            throw new ServiceException(PoetryExceptionEnum.SIGN_ERROR);
        }
        return true;
    }
}
