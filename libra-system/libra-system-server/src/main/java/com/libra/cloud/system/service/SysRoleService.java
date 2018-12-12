package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysRole;
import com.libra.cloud.system.exception.enums.SystemExceptionEnum;
import com.libra.cloud.system.mapper.SysRoleMapper;
import com.libra.core.exception.RequestEmptyException;
import com.libra.core.exception.ServiceException;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {
    @Autowired
    SysRoleResourceService sysRoleResourceService;

    /**
     * 添加角色
     *
     * @param sysRole 角色
     */
    public void addRole(SysRole sysRole) {
        if (EmptyUtil.isOneEmpty(sysRole, sysRole.getName(), sysRole.getTitle())) {
            throw new RequestEmptyException();
        }
        SysRole select = new SysRole();
        select.setName(sysRole.getName());
        if (baseMapper.selectOne(select) != null) {
            throw new ServiceException(SystemExceptionEnum.ROLE_EXIST);
        }
        insert(sysRole);
    }

    /**
     * 更新角色
     *
     * @param sysRole 角色
     */
    public void updateRole(SysRole sysRole) {
        if (EmptyUtil.isOneEmpty(sysRole, sysRole.getRoleId())) {
            throw new RequestEmptyException();
        }

        SysRole oldRole = baseMapper.selectById(sysRole.getRoleId());
        if (oldRole == null) {
            throw new ServiceException(SystemExceptionEnum.ROLE_NOT_FOUND);
        }
        ToolUtil.copyProperties(sysRole, oldRole);
        updateById(oldRole);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    public void deleteRole(Integer roleId) {
        if (EmptyUtil.isEmpty(roleId)) {
            throw new RequestEmptyException();
        }
        deleteById(roleId);
        sysRoleResourceService.deleteRoleResource(roleId);
    }

    /**
     * 获取所有角色
     *
     * @return 角色列表
     */
    public List<SysRole> getRoleList() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    /**
     * 获取用户的所有角色
     *
     * @param userId 用户id
     * @return
     */
    public List<SysRole> getRoleList(Integer userId) {
        return baseMapper.selectUserRoleByUserId(userId);
    }
}
