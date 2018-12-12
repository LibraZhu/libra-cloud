package com.libra.cloud.gateway.filter;

import com.libra.cloud.gateway.constants.AuthConstants;
import com.libra.cloud.gateway.constants.ZuulFiltersOrder;
import com.libra.cloud.gateway.service.TokenValidateService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 权限校验的过滤器
 */
public class JwtTokenFilter extends ZuulFilter {
    @Autowired
    private TokenValidateService tokenValidateService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulFiltersOrder.JWT_TOKEN_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //登陆接口和验证token放过资源过滤
        if (request.getServletPath().contains(AuthConstants.AUTH_ACTION_URL) ||
                request.getServletPath().contains(AuthConstants.VALIDATE_TOKEN_URL)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        tokenValidateService.doValidate(request);

        return null;
    }
}
