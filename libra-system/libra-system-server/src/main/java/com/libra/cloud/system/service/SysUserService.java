package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.api.context.LoginUser;
import com.libra.cloud.system.api.exception.enums.AuthExceptionEnum;
import com.libra.cloud.system.constants.SystemConstants;
import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRole;
import com.libra.cloud.system.entity.SysUser;
import com.libra.cloud.system.mapper.SysResourceMapper;
import com.libra.cloud.system.mapper.SysRoleMapper;
import com.libra.cloud.system.mapper.SysUserMapper;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/11/29
 * @description
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 用户登录，登录成功返回token
     */
    public String login(String username, String password) {

        //查询账号是否存在
        SysUser sysUser = null;
        List<SysUser> accounts = this.selectList(new EntityWrapper<SysUser>().eq("ACCOUNT", username));
        if (accounts != null && accounts.size() > 0) {
            sysUser = accounts.get(0);
        } else {
            throw new ServiceException(AuthExceptionEnum.USER_NOT_FOUND);
        }

        //校验账号密码是否正确
        String md5Hex = ToolUtil.md5Hex(password + sysUser.getSalt());
        if (!md5Hex.equals(sysUser.getPassword())) {
            throw new ServiceException(AuthExceptionEnum.INVALID_PWD);
        }

        //生成token
        String jwtToken = jwtTokenUtil.generateToken(sysUser.getUserId().toString(), null);

        //token放入缓存
        LoginUser loginUser = new LoginUser();
        loginUser.setAccountId(sysUser.getUserId());
        //角色
        List<SysRole> roleList = sysRoleMapper.selectUserRoleByUserId(sysUser.getUserId());
        Set<Integer> roleIdList = new HashSet<>();
        for (SysRole role : roleList) {
            roleIdList.add(role.getRoleId());
        }
        loginUser.setRoleIds(roleIdList);
        //资源
        List<SysResource> resourceList = sysResourceMapper.selectUserResourceByUserId(sysUser.getUserId());
        Set<String> resourceUrlList = new HashSet<>();
        for (SysResource resource : resourceList) {
            resourceUrlList.add(resource.getUrl());
        }
        loginUser.setResourceUrls(resourceUrlList);
        BoundValueOperations<String, Object> opts = redisTemplate.boundValueOps(SystemConstants.LOGIN_USER_CACHE_PREFIX + jwtToken);
        opts.set(loginUser, SystemConstants.DEFAULT_LOGIN_TIME_OUT_SECS, TimeUnit.SECONDS);

        return jwtToken;
    }

    /**
     * 校验token是否正确
     */
    public boolean checkToken(String token) {

        //先校验jwt是否正确
        if (!jwtTokenUtil.checkToken(token)) {
            return false;
        }

        //校验缓存是否有token
        BoundValueOperations<String, Object> opts = redisTemplate.boundValueOps(SystemConstants.LOGIN_USER_CACHE_PREFIX + token);
        LoginUser loginUser = (LoginUser) opts.get();
        if (loginUser == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登录
     */
    public void logout(String token) {
        redisTemplate.delete(SystemConstants.LOGIN_USER_CACHE_PREFIX + token);
    }

    /**
     * 获取登录用户通过token
     */
    public LoginUser getLoginUserByToken(String token) {
        BoundValueOperations<String, Object> opts = redisTemplate.boundValueOps(SystemConstants.LOGIN_USER_CACHE_PREFIX + token);
        Object loginUser = opts.get();
        if (loginUser != null) {
            return (LoginUser) loginUser;
        } else {
            return null;
        }
    }
}
