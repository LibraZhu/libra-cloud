package com.libra.cloud.poetry.service;

import com.libra.cloud.poetry.entity.Like;
import com.libra.cloud.poetry.mapper.LikeMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class LikeService extends ServiceImpl<LikeMapper, Like> {

}
