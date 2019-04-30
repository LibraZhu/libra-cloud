package com.libra.cloud.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.system.entity.SysAd;

import java.util.List;

/**
 * <p>
 * 广告信息表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
public interface SysAdMapper extends BaseMapper<SysAd> {
    List<SysAd> selectAdByType(int type);
}
