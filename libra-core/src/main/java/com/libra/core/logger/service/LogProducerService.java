package com.libra.core.logger.service;

import com.libra.core.logger.entity.SendingCommonLog;
import com.libra.core.logger.entity.SendingTCLog;
import com.libra.core.logger.entity.SendingTraceLog;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 发送日志到消息队列的接口类
 */
public interface LogProducerService {
    /**
     * 发送日志
     *
     * @author yaoliguo
     * @date 2018-04-25 10:37
     */
    void sendMsg(SendingCommonLog log);

    /**
     * 发送trace日志
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午7:16
     */
    void sendTraceMsg(SendingTraceLog sendingTraceLog);

    /**
     * 发送接口调用时间日志
     *
     * @author fengshuonan
     * @Date 2018/5/15 下午7:16
     */
    void sendTcMsg(SendingTCLog sendingTCLog);
}
