package com.libra.cloud.gateway.consumer;

import com.libra.core.api.AuthService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 鉴权服务的消费者
 */
@FeignClient("libra-system-server")
public interface AuthServiceConsumer extends AuthService {
}
