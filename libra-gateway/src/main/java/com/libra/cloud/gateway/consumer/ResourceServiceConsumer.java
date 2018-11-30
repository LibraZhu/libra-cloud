package com.libra.cloud.gateway.consumer;

import com.libra.core.api.ResourceService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 资源服务的消费者
 */
@FeignClient("libra-system")
public interface ResourceServiceConsumer extends ResourceService {
}
