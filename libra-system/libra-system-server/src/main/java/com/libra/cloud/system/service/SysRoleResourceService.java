package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysRoleResource;
import com.libra.cloud.system.entity.SysUserRole;
import com.libra.cloud.system.mapper.SysRoleResourceMapper;
import com.libra.cloud.system.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

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
}
