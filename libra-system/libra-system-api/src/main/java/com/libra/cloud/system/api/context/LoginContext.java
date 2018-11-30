package com.libra.cloud.system.api.context;

import com.libra.core.api.AuthService;
import com.libra.core.exception.CoreExceptionEnum;
import com.libra.core.exception.ServiceException;
import com.libra.core.user.AbstractLoginUser;
import com.libra.core.user.context.AbstractLoginContext;
import com.libra.core.user.context.LoginUserHolder;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.HttpContext;
import com.libra.core.util.SpringContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 登录信息上下文
 */
public class LoginContext implements AbstractLoginContext {
    private AuthService authService;

    public LoginContext(AuthService authService) {
        this.authService = authService;
    }

    public static LoginContext me() {
        return SpringContextHolder.getBean(LoginContext.class);
    }

    /**
     * 获取当前用户的token
     * <p>
     * 先判断header中是否有Authorization字段，
     * 如果header中没有这个字段，则检查请求参数中是否带token，
     * 如果任意一个地方有这个值，则返回这个值
     * 两个地方都没有token，则抛出没有登录用户异常
     */
    @Override
    public String getCurrentUserToken() {
        HttpServletRequest request = HttpContext.getRequest();
        if (request == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        //如果请求是在http环境下，则有request对象
        String authorization = request.getHeader("Authorization");
        if (EmptyUtil.isNotEmpty(authorization)) {
            return authorization;
        } else {
            String token = request.getParameter("token");
            if (EmptyUtil.isNotEmpty(token)) {
                return token;
            } else {
                throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
            }
        }
    }

    /**
     * 获取当前用户
     * <p>
     * 先从ThreadLocal中拿user，如果有值就直接返回，没取到再去调用远程服务,调用完远程服务把获取到的user放到Threadlocal里
     */
    @Override
    public <T extends AbstractLoginUser> T getLoginUser() {
        AbstractLoginUser currentUser = LoginUserHolder.get();
        if (currentUser != null) {
            return (T) currentUser;
        } else {
            String token = getCurrentUserToken();
            return (T) this.authService.getLoginUserByToken(token);
        }
    }

    /**
     * 获取当前登录用户的账户id
     */
    public Long getUserAccountId() {
        AbstractLoginUser abstractLoginUser = this.getLoginUser();
        if (abstractLoginUser == null) {
            return null;
        } else {
            return abstractLoginUser.getUserUniqueId();
        }
    }
}
