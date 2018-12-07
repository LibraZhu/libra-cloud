package com.libra.cloud.system.provider;

import com.libra.core.api.AuthService;
import com.libra.cloud.system.context.LoginUser;
import com.libra.cloud.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 鉴权服务的提供者
 */
@RestController
@Primary
public class AuthServiceProvider implements AuthService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public String login(@RequestParam("account") String account, @RequestParam("password") String password) {
        return sysUserService.login(account, password);
    }

    @Override
    public boolean checkToken(@RequestParam("token") String token) {
        return sysUserService.checkToken(token);
    }

    @Override
    public void logout(@RequestParam("token") String token) {
        sysUserService.logout(token);
    }

    @Override
    public LoginUser getLoginUserByToken(@RequestParam("token") String token) {
        return sysUserService.getLoginUserByToken(token);
    }
}
