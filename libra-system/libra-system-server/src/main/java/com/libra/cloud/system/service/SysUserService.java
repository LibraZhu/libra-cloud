package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.api.context.LoginUser;
import com.libra.cloud.system.api.entity.SysUser;
import com.libra.cloud.system.constants.SystemConstants;
import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRole;
import com.libra.cloud.system.entity.SysUserRole;
import com.libra.cloud.system.exception.enums.SystemExceptionEnum;
import com.libra.cloud.system.mapper.SysUserMapper;
import com.libra.core.exception.RequestEmptyException;
import com.libra.core.exception.ServiceException;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysResourceService sysResourceService;

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
            throw new ServiceException(SystemExceptionEnum.USER_NOT_FOUND);
        }

        //校验账号密码是否正确
        String md5Hex = ToolUtil.md5Hex(password + sysUser.getSalt());
        if (!md5Hex.equals(sysUser.getPassword())) {
            throw new ServiceException(SystemExceptionEnum.INVALID_PWD);
        }

        //生成token
        String jwtToken = jwtTokenUtil.generateToken(sysUser.getUserId().toString(), null);

        //token放入缓存
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(sysUser);
        //角色
        List<SysRole> roleList = getUserRoleList(sysUser.getUserId());
        Set<Integer> roleIdList = new HashSet<>();
        Set<String> roleCodeList = new HashSet<>();
        for (SysRole role : roleList) {
            roleIdList.add(role.getRoleId());
            roleCodeList.add(role.getName());
        }
        loginUser.setRoleIds(roleIdList);
        loginUser.setRoleCodes(roleCodeList);
        //资源
        List<SysResource> resourceList = getUserResourceList(sysUser.getUserId());
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

    /**
     * 添加用户
     *
     * @param sysUser 用户
     */
    public SysUser addUser(SysUser sysUser) {
        if (EmptyUtil.isOneEmpty(sysUser, sysUser.getAccount(), sysUser.getPassword())) {
            throw new RequestEmptyException();
        }
        SysUser select = new SysUser();
        select.setAccount(sysUser.getAccount());
        if (baseMapper.selectOne(select) != null) {
            throw new ServiceException(SystemExceptionEnum.USER_EXIST);
        }

        String salt = ToolUtil.getSalt();
        sysUser.setSalt(salt);
        sysUser.setPassword(ToolUtil.md5Hex(sysUser.getPassword() + sysUser.getSalt()));
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setStatus(1);
        insert(sysUser);
        return baseMapper.selectOne(select);
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户
     */
    public SysUser updateUser(SysUser sysUser) {
        if (EmptyUtil.isOneEmpty(sysUser, sysUser.getAccount(), sysUser.getPassword())) {
            throw new RequestEmptyException();
        }
        SysUser oldUser = baseMapper.selectById(sysUser.getUserId());
        if (oldUser == null) {
            throw new ServiceException(SystemExceptionEnum.USER_NOT_FOUND);
        }
        ToolUtil.copyProperties(sysUser, oldUser);
        updateById(oldUser);
        return sysUser;
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    public void deleteUser(Integer userId) {
        if (EmptyUtil.isEmpty(userId)) {
            throw new RequestEmptyException();
        }
        deleteById(userId);
        deleteUserAllRole(userId);
    }

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    public List<SysUser> getUserList() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    /**
     * 添加用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    public void addUserRole(Integer userId, Integer roleId) {
        if (EmptyUtil.isOneEmpty(userId, roleId)) {
            throw new RequestEmptyException();
        }
        if (baseMapper.selectById(userId) == null) {
            throw new ServiceException(SystemExceptionEnum.USER_NOT_FOUND);
        }
        if (sysRoleService.selectById(roleId) == null) {
            throw new ServiceException(SystemExceptionEnum.ROLE_NOT_FOUND);
        }
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(userId);
        if (sysUserRoleService.getSysUserRole(userId, roleId) == null) {
            sysUserRoleService.insert(sysUserRole);
        }
    }

    /**
     * 删除用户角色
     *
     * @param userId 用户
     * @param roleId 角色
     */
    public void deleteUserRole(Integer userId, Integer roleId) {
        if (EmptyUtil.isOneEmpty(userId, roleId)) {
            throw new RequestEmptyException();
        }
        SysUserRole sysUserRole = sysUserRoleService.getSysUserRole(userId, roleId);
        if (sysUserRole != null) {
            sysUserRoleService.deleteById(sysUserRole.getUserRoleId());
        }
    }

    /**
     * 删除用户所有角色
     *
     * @param userId 用户
     */
    public void deleteUserAllRole(Integer userId) {
        if (EmptyUtil.isOneEmpty(userId)) {
            throw new RequestEmptyException();
        }
        sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("USER_ID", userId));
    }

    /**
     * 获取用户角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    public List<SysRole> getUserRoleList(Integer userId) {
        return sysRoleService.getRoleList(userId);
    }

    /**
     * 获取用户资源列表
     *
     * @param userId 用户id
     * @return 资源列表
     */
    public List<SysResource> getUserResourceList(Integer userId) {
        return sysResourceService.getUserResourceList(userId);
    }
}
