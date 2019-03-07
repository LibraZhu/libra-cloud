package com.libra.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.libra.cloud.gateway.constants.Constants;
import com.libra.cloud.gateway.consumer.AuthServiceConsumer;
import com.libra.cloud.gateway.consumer.ResourceServiceConsumer;
import com.libra.cloud.gateway.exception.AuthExceptionEnum;
import com.libra.cloud.gateway.response.ErrorResponseData;
import com.libra.cloud.gateway.response.ResponseData;
import com.libra.cloud.system.api.entity.ResourceDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Libra
 * @date 2018/11/30
 * @description requestNo生成过滤器
 */
public class AccessGatewayFilter implements GlobalFilter {

    @Autowired
    private ResourceServiceConsumer resourceServiceConsumer;

    @Autowired
    private AuthServiceConsumer authServiceConsumer;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        //生成唯一请求号uuid
        String requestNo = IdWorker.getIdStr();
        mutate.header(Constants.REQUEST_NO_HEADER_NAME, requestNo);

        String originalUri = "";
        LinkedHashSet requiredAttribute = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()) {
                URI next = iterator.next();
                if ("gateway".equals(next.getHost())) {
                    originalUri = next.getPath();
                }
            }
        }
        //登陆接口和验证token放过资源过滤
        if (originalUri.startsWith("/admin")) {
            String requestUri = request.getPath().pathWithinApplication().value();
            if (requestUri.contains(Constants.AUTH_ACTION_URL) ||
                    requestUri.contains(Constants.VALIDATE_TOKEN_URL)) {
                ServerHttpRequest build = mutate.build();
                return chain.filter(exchange.mutate().request(build).build());
            }
            //获取当前接口是否需要鉴权
            ResourceDefinition currentResource = resourceServiceConsumer.getResourceByUrl(requestUri);
            //判断是否需要登录
            if (currentResource != null && currentResource.getRequiredLogin()) {
                List<String> strings = request.getHeaders().get(Constants.REQUEST_AUTH_HEADER);
                String authToken = null;
                if (strings != null) {
                    authToken = strings.get(0);
                }
                if (StringUtils.isBlank(authToken)) {
                    strings = request.getQueryParams().get("token");
                    if (strings != null) {
                        authToken = strings.get(0);
                    }
                }
                //验证token是否正确
                boolean check = authServiceConsumer.checkToken(authToken);
                if (!check) {
                    return getVoidMono(exchange, ErrorResponseData.error(AuthExceptionEnum.TOKEN_ERROR.getCode(), AuthExceptionEnum.TOKEN_ERROR.getMessage()));
                }
                //判断本接口是否需要url资源过滤
                if (currentResource.getRequiredPermission()) {
                    Set<String> permissionUrls = authServiceConsumer.getLoginUserByToken(authToken).getResourceUrls();
                    if (permissionUrls == null || !permissionUrls.contains(requestUri)) {
                        return getVoidMono(exchange, ErrorResponseData.error(AuthExceptionEnum.NO_PERMISSION.getCode(), AuthExceptionEnum.NO_PERMISSION.getMessage()));
                    }
                }
            }
        }

        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * 网关抛异常
     *
     * @param body
     */
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, ResponseData body) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        byte[] bytes = JSONObject.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
