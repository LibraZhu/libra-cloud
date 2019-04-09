package com.libra.cloud.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.system.entity.SysConfig;

import java.util.List;

/**
 * <p>
 * 配置信息表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    List<SysConfig> selectConfigByType(int type);
}
