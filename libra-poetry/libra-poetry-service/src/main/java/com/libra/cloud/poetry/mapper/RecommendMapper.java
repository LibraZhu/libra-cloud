package com.libra.cloud.poetry.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.poetry.entity.Poetry;
import com.libra.cloud.poetry.entity.Recommend;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 推荐表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
public interface RecommendMapper extends BaseMapper<Recommend> {

    List<Poetry> selectRecommendByDate();
}
