package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Like;
import com.libra.cloud.poetry.mapper.LikeMapper;
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

    /**
     * 获取评论的点赞
     *
     * @param userId 用户
     * @param id     评论id
     * @return 点赞
     */
    public Like selectCommentLike(Integer userId, Long id) {
        Like like = new Like();
        like.setUid(userId);
        like.setType(1);
        like.setTypeId(id);
        return baseMapper.selectOne(like);
    }
}
