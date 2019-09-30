package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Favorite;
import com.libra.cloud.poetry.mapper.FavoriteMapper;
import com.libra.cloud.poetry.model.FavoriteModel;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
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
    /**
     * 判断诗词是否是用户收藏
     *
     * @param poetryId 诗词id
     * @return 是否收藏
     */
    public Boolean checkUserFavByPoetryId(Integer userId, Integer poetryId) {
        Favorite favorite = new Favorite();
        favorite.setPoetryId(poetryId);
        favorite.setUid(userId);
        favorite = baseMapper.selectOne(favorite);
        if (favorite == null) {
            return false;
        } else {
            return favorite.getStatus() == 1;
        }
    }

    /**
     * 获取用户的收藏列表
     *
     * @param userId 用户id
     * @return 收藏列表
     */
    public PageResult<FavoriteModel> selectUserFavoritePage(Integer userId) {
        Page<FavoriteModel> page = PageFactory.defaultPage();
        page.setRecords(baseMapper.selectFavoritePage(page, userId));
        return new PageResult<>(page);
    }

    /**
     * 获取用户的某个诗词收藏
     *
     * @param userId   用户id
     * @param poetryId 诗词id
     * @return
     */
    public Favorite selectFavorite(Integer userId, Integer poetryId) {
        Favorite favorite = new Favorite();
        favorite.setUid(userId);
        favorite.setPoetryId(poetryId);
        return baseMapper.selectOne(favorite);
    }
}
