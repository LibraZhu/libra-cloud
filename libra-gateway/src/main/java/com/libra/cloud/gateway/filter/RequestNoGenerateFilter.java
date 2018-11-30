package com.libra.cloud.gateway.filter;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.libra.cloud.gateway.constants.ZuulFiltersOrder;
import com.libra.core.constants.Constants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Libra
 * @date 2018/11/30
 * @description requestNo生成过滤器
 */
public class RequestNoGenerateFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return ZuulFiltersOrder.REQUEST_NO_GENERATE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();

        //生成唯一请求号uuid
        String requestNo = IdWorker.getIdStr();
        currentContext.set(Constants.REQUEST_NO_HEADER_NAME, requestNo);
        currentContext.addZuulRequestHeader(Constants.REQUEST_NO_HEADER_NAME, requestNo);
        response.addHeader(Constants.REQUEST_NO_HEADER_NAME, requestNo);

        return null;
    }
}
