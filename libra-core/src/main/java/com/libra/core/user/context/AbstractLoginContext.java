package com.libra.core.user.context;

import com.libra.core.user.AbstractLoginUser;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 快速获取登录信息上下文
 */
public interface AbstractLoginContext {
    /**
     * 获取当前用户的token
     */
    String getCurrentUserToken();

    /**
     * 获取当前用户
     */
    <T extends AbstractLoginUser> T getLoginUser();
}
