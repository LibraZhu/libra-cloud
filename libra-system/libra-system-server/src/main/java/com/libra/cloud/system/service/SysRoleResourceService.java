package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRoleResource;
import com.libra.cloud.system.mapper.SysRoleResourceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色资源关系表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysRoleResourceService extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> {

    /**
     * 删除某角色关联的资源
     *
     * @param roleId 角色id
     */
    public void deleteRoleResource(Integer roleId) {
        baseMapper.delete(new EntityWrapper<SysRoleResource>().eq("ROLE_ID", roleId));
    }

    public void insertOrUpdateAdminResource(List<SysResource> list) {
        List<SysRoleResource> resources = baseMapper.selectList(new EntityWrapper<SysRoleResource>().eq("ROLE_ID", 1));
        List<String> codeList = new ArrayList<>();
        for (SysRoleResource resource : resources) {
            codeList.add(resource.getCode());
        }
        List<SysRoleResource> insertList = new ArrayList<>();
        for (SysResource resource : list) {
            if (!codeList.contains(resource.getCode())) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setCode(resource.getCode());
                sysRoleResource.setRoleId(1);
                insertList.add(sysRoleResource);
            }
        }
        if (insertList.size() > 0) {
            insertBatch(insertList);
        }
    }
}
