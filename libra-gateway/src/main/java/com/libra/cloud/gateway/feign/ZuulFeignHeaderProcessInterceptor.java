package com.libra.cloud.gateway.feign;

import com.libra.core.constants.Constants;
import com.libra.core.feign.FeignHeaderProcessInterceptor;
import com.netflix.zuul.context.RequestContext;
import feign.RequestTemplate;

/**
 * @author Libra
 * @date 2018/11/30
 * @description zuul对feign拦截器的拓展
 */
public class ZuulFeignHeaderProcessInterceptor extends FeignHeaderProcessInterceptor {
    @Override
    public void addOtherHeaders(RequestTemplate requestTemplate) {

        RequestContext currentContext = RequestContext.getCurrentContext();
        Object contextObject = currentContext.get(Constants.REQUEST_NO_HEADER_NAME);

        requestTemplate.header(Constants.REQUEST_NO_HEADER_NAME, contextObject == null ? "" : contextObject.toString());
    }
}
