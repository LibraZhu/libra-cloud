package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.mapper.SysResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysResourceService extends ServiceImpl<SysResourceMapper, SysResource> {
    /**
     * 获取所有资源列表
     *
     * @return 资源列表
     */
    public List<SysResource> getResourceList() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    /**
     * 获取角色的资源列表
     *
     * @param roleId 角色id
     * @return 资源列表
     */
    public List<SysResource> getRoleResourceList(Integer roleId) {
        return baseMapper.selectRoleResourceByRoleId(roleId);
    }

    /**
     * 获取角色的资源url列表
     *
     * @param roleId 角色id
     * @return 资源url列表
     */
    public List<String> getRoleResourceUrlList(Integer roleId) {
        return baseMapper.selectRoleResourceUrlByRoleId(roleId);
    }

    /**
     * 获取用户的资源列表
     *
     * @param userId 用户id
     * @return 资源列表
     */
    public List<SysResource> getUserResourceList(Integer userId) {
        return baseMapper.selectRoleResourceByRoleId(userId);
    }
}
