package com.libra.core.base.api;

import com.libra.core.validator.BaseValidatingParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 远程服务的参数的基类
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam {
    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;
}
