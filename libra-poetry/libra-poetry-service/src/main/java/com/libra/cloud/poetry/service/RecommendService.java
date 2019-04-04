package com.libra.cloud.poetry.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Poetry;
import com.libra.cloud.poetry.entity.Recommend;
import com.libra.cloud.poetry.mapper.RecommendMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 推荐表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class RecommendService extends ServiceImpl<RecommendMapper, Recommend> {
    /**
     * 获取推荐诗文
     *
     * @return 推荐诗文
     */
    public List<Poetry> selectList() {
        return baseMapper.selectRecommendByDate(DateUtil.parseDate(DateUtil.today()));
    }
}
