package com.libra.core.logger.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 请求时间记录日志
 */
@Data
public class SendingTCLog {
    private Long id;

    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 花费时间（毫秒）
     */
    private Long useTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
