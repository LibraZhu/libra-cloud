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
    // 根据用户id获取所属的资源
    List<SysResource> selectUserResourceByUserId(Integer userId);

}
