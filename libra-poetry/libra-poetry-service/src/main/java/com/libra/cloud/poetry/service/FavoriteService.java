package com.libra.cloud.poetry.service;

import com.libra.cloud.poetry.entity.Favorite;
import com.libra.cloud.poetry.mapper.FavoriteMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class FavoriteService extends ServiceImpl<FavoriteMapper, Favorite> {

}
