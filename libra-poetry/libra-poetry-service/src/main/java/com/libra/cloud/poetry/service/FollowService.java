package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Follow;
import com.libra.cloud.poetry.mapper.FollowMapper;
import com.libra.cloud.poetry.model.FollowModel;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    /**
     * 获取关注用户列表
     *
     * @param uid 用户id
     * @return 关注列表
     */
    public PageResult selectFollowPage(Integer uid) {
        Page<FollowModel> page = PageFactory.defaultPage();
        page.setRecords(baseMapper.selectFollowPage(page, uid));
        return new PageResult<>(page);
    }

    /**
     * 获取粉丝里诶包
     *
     * @param uid 用户id
     * @return 粉丝列表
     */
    public PageResult selectFansPage(Integer uid) {
        Page<FollowModel> page = PageFactory.defaultPage();
        page.setRecords(baseMapper.selectFansPage(page, uid));
        return new PageResult<>(page);
    }

    public boolean isFollow(Integer uid, Integer followId) {
        int count = selectCount(new EntityWrapper<Follow>().eq("uid", uid).and().eq("followed_id", followId).eq("status", 1));
        return count > 0;
    }

    /**
     * 关注/取消关注
     *
     * @param uid      用户id
     * @param followId 关注用户id
     * @param status   状态：1关注；0取消关注
     * @return 关注对象
     */
    public Integer follow(Integer uid, Integer followId, Integer status) {
        Follow follow = new Follow();
        follow.setUid(uid);
        follow.setFollowedId(followId);
        follow = baseMapper.selectOne(follow);
        if (follow == null) {
            follow = new Follow();
            follow.setUid(uid);
            follow.setFollowedId(followId);
            follow.setStatus(status);
            Date data = new Date();
            follow.setCreateTime(data);
            follow.setUpdateTime(data);
        } else {
            follow.setStatus(status);
            Date data = new Date();
            follow.setUpdateTime(data);
        }
        insertOrUpdate(follow);
        return status;
    }
}
