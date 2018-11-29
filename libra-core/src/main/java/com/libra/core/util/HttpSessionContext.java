package com.libra.core.util;

import javax.servlet.http.HttpSession;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 非Controller中获取当前session的工具类
 */
public class HttpSessionContext {
    private static ThreadLocal<HttpSession> tl = new ThreadLocal<HttpSession>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
