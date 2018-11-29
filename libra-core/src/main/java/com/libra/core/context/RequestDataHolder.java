package com.libra.core.context;

import com.libra.core.reqres.request.RequestData;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 请求数据的临时容器
 */
public class RequestDataHolder {
    private static ThreadLocal<RequestData> holder = new ThreadLocal<>();

    public static void put(RequestData s) {
        holder.set(s);
    }

    public static RequestData get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
