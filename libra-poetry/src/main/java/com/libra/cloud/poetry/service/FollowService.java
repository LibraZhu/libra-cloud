package com.libra.cloud.poetry.service;

import com.libra.cloud.poetry.entity.Follow;
import com.libra.cloud.poetry.mapper.FollowMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class FollowService extends ServiceImpl<FollowMapper, Follow> {

}
