package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysUserRole;
import com.libra.cloud.system.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysUserRoleService extends ServiceImpl<SysUserRoleMapper, SysUserRole> {

    /**
     * 获取用户的每个角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return
     */
    public SysUserRole getSysUserRole(Integer userId, Integer roleId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(userId);
        return baseMapper.selectOne(sysUserRole);
    }
}
