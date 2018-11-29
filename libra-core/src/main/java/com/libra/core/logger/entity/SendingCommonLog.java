package com.libra.core.logger.entity;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 日志实体类
 */
@Data
public class SendingCommonLog {
    /**
     * 日志唯一id
     */
    private Long id;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 日志级别 info，error，warn，debug
     */
    private String level;

    /**
     * 类名
     */
    private String className;

    /**
     * 打日志的方法的名称
     */
    private String methodName;

    /**
     * 远程访问IP地址
     */
    private String ip;

    /**
     * 用户账号id
     */
    private String accountId;

    /**
     * 日志号
     */
    private String requestNo;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求的数据内容
     */
    private String requestData;

    /**
     * 日志详情
     */
    private String logContent;

    /**
     * 创建时间
     */
    private Long createTimestamp;
}
