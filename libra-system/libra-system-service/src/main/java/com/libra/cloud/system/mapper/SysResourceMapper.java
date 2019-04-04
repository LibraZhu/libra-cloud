package com.libra.cloud.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    /**
     * 根据用户id获取所属的资源
     *
     * @param userId 用户id
     * @return 资源列表
     */
    List<SysResource> selectUserResourceByUserId(Integer userId);

    /**
     * 根据角色id获取所属的资源
     *
     * @param roleId 角色id
     * @return 资源列表
     */
    List<SysResource> selectRoleResourceByRoleId(Integer roleId);

    /**
     * 根据角色id获取所属的资源url
     *
     * @param roleId 角色id
     * @return 资源url列表
     */
    List<String> selectRoleResourceUrlByRoleId(Integer roleId);

}
