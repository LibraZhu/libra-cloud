package com.libra.core.logger.context;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 基于调用链的服务治理系统的设计（requestNo以及当前节点的spanId和parentSpanId的临时存储器）
 */
public class ParentSpanIdHolder {
    private static final ThreadLocal<String> PARENT_SPAN_ID_CONTEXT = new ThreadLocal<>();

    public static void set(String parentSpanId) {
        PARENT_SPAN_ID_CONTEXT.set(parentSpanId);
    }

    public static String get() {
        return PARENT_SPAN_ID_CONTEXT.get();
    }

    public static void remove() {
        PARENT_SPAN_ID_CONTEXT.remove();
    }
}
