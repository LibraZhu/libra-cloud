package com.libra.cloud.system.interceptor;

import com.libra.cloud.system.api.entity.ResourceDefinition;
import com.libra.cloud.system.exception.enums.SystemExceptionEnum;
import com.libra.cloud.system.service.SysUserService;
import com.libra.core.exception.ServiceException;
import com.libra.core.util.HttpContext;
import com.libra.scanner.factory.ApiResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @author Libra
 * @date 2019/3/21
 * @description
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    ApiResourceFactory apiResourceFactory;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestUri = null;
        if (request != null) {
            requestUri = request.getRequestURI();
            ResourceDefinition currentResource = apiResourceFactory.getResourceByUrl(requestUri);
            if (currentResource != null && currentResource.getRequiredLogin()) {
                String authToken = HttpContext.getToken();
                if (authToken == null) {
                    throw new ServiceException(SystemExceptionEnum.TOKEN_ERROR);
                }
                //验证token是否正确
                boolean check = sysUserService.checkToken(authToken);
                if (!check) {
                    throw new ServiceException(SystemExceptionEnum.TOKEN_ERROR);
                }
                //判断本接口是否需要url资源过滤
                if (currentResource.getRequiredPermission()) {
                    Set<String> permissionUrls = sysUserService.getLoginUserByToken(authToken).getResourceUrls();
                    if (permissionUrls == null || !permissionUrls.contains(requestUri)) {
                        throw new ServiceException(SystemExceptionEnum.NO_PERMISSION);
                    }
                }
            }
        }
        return true;
    }
}
