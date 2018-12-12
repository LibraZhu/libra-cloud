package com.libra.cloud.system.api;

import com.libra.cloud.system.api.context.LoginUser;
import com.libra.core.user.AbstractLoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 鉴权服务, 提供颁发, 校验, 注销等方法
 */
@RequestMapping("/api/auth")
public interface AuthService {

    /**
     * 校验token(true-校验成功,false-校验失败)
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    boolean checkToken(@RequestParam("token") String token);

    /**
     * 通过token获取用户信息
     */
    @RequestMapping(value = "/getLoginUserByToken", method = RequestMethod.POST)
    LoginUser getLoginUserByToken(@RequestParam("token") String token);
}
