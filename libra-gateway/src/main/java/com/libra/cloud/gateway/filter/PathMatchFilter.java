package com.libra.cloud.gateway.filter;

import com.libra.cloud.gateway.constants.AuthConstants;
import com.libra.cloud.gateway.constants.ZuulFiltersOrder;
import com.libra.cloud.gateway.consumer.AuthServiceConsumer;
import com.libra.cloud.gateway.consumer.ResourceServiceConsumer;
import com.libra.cloud.gateway.exception.AuthExceptionEnum;
import com.libra.core.exception.ServiceException;
import com.libra.core.resouce.ResourceDefinition;
import com.libra.core.user.AbstractLoginUser;
import com.libra.core.util.HttpContext;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 请求路径权限过滤器
 */
public class PathMatchFilter extends ZuulFilter {

    @Autowired
    private ResourceServiceConsumer resourceServiceConsumer;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulFiltersOrder.PATH_MATCH_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //登陆接口和验证token放过资源过滤
        if (request.getServletPath().equals(AuthConstants.AUTH_ACTION_URL) ||
                request.getServletPath().equals(AuthConstants.VALIDATE_TOKEN_URL)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String requestUri = request.getRequestURI();

        //如果是admin开头的url，则为系统后台
        if (requestUri.startsWith("/admin")) {
            requestUri = requestUri.substring(6);
            ResourceDefinition currentResource = resourceServiceConsumer.getResourceByUrl(requestUri);
            if (currentResource == null) {
                return null;
            } else {

                //判断如果本接口不需要登录直接略过,不登录获取不到用户token
                if (!currentResource.getRequiredLogin()) {
                    return null;
                }

                //判断本接口是否需要url资源过滤
                if (currentResource.getRequiredPermission()) {
                    final String sysToken = HttpContext.getToken();
                    Set<String> permissionUrls = authServiceConsumer.getLoginUserByToken(sysToken).getResourceUrls();
                    if (permissionUrls == null) {
                        throw new ServiceException(AuthExceptionEnum.NO_PERMISSION);
                    }
                    boolean hasPermission = permissionUrls.contains(requestUri);
                    if (hasPermission) {
                        return null;
                    } else {
                        throw new ServiceException(AuthExceptionEnum.NO_PERMISSION);
                    }
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
