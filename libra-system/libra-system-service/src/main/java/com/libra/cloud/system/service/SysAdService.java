package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysAd;
import com.libra.cloud.system.mapper.SysAdMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysAdService extends ServiceImpl<SysAdMapper, SysAd> {
    /**
     * 获取banner类型的广告信息
     *
     * @return 配置信息
     */
    public List<SysAd> selectAdForBanner() {
        return baseMapper.selectAdByType(1);
    }

    /**
     * 获取所有广告
     *
     * @return
     */
    public List<SysAd> getAdList() {
        return baseMapper.selectList(new EntityWrapper<>());
    }
}
