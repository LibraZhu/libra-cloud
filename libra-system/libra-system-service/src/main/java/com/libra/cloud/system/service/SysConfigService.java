package com.libra.cloud.system.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.system.entity.SysConfig;
import com.libra.cloud.system.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 配置信息表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@Service
public class SysConfigService extends ServiceImpl<SysConfigMapper, SysConfig> {

    /**
     * 获取配置信息
     *
     * @return 配置信息
     */
    public List<SysConfig> getConfigList() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    /**
     * 获取类型的配置信息
     *
     * @param type 类型
     * @return 配置信息
     */
    public List<SysConfig> getConfigListByType(int type) {
        return baseMapper.selectConfigByType(type);
    }

}
