package com.libra.core.util;

import com.alibaba.fastjson.JSON;
import com.libra.core.exception.CoreExceptionEnum;
import com.libra.core.exception.ServiceException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 渲染工具类
 */
public class RenderUtil {
    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new ServiceException(CoreExceptionEnum.WRITE_ERROR);
        }
    }
}
