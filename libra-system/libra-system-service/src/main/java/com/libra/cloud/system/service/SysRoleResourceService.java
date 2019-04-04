package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

    public void insertOrUpdateAdminResource(List<String> list) {
        insertOrUpdateRoleResource(1, list);
    }

    /**
     * 修改角色关联的资源
     *
     * @param roleId 角色id
     * @param list   资源列表
     */
    public void insertOrUpdateRoleResource(Integer roleId, List<String> list) {
        List<SysRoleResource> resources = baseMapper.selectList(new EntityWrapper<SysRoleResource>().eq("ROLE_ID", roleId));
        List<String> existList = new ArrayList<>();
        List<Integer> deleteList = new ArrayList<>();
        List<SysRoleResource> insertList = new ArrayList<>();

        for (SysRoleResource exist : resources) {
            existList.add(exist.getCode());
        }
        for (int i = 0; i < existList.size(); i++) {
            String code = existList.get(i);
            if (!list.contains(code)) {
                deleteList.add(resources.get(i).getRoleResourceId());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String code = list.get(i);
            if (!existList.contains(code)) {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setCode(code);
                sysRoleResource.setRoleId(roleId);
                insertList.add(sysRoleResource);
            }
        }
        if (insertList.size() > 0) {
            insertBatch(insertList);
        }
        if (deleteList.size() > 0) {
            deleteBatchIds(deleteList);
        }
    }
}
