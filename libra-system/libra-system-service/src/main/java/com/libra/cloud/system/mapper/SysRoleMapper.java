package com.libra.cloud.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    // 根据用户id获取所属的角色
    List<SysRole> selectUserRoleByUserId(Integer userId);

}
