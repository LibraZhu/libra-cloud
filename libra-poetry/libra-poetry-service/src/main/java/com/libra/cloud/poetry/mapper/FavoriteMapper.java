package com.libra.cloud.poetry.mapper;

import com.libra.cloud.poetry.entity.Favorite;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.poetry.model.CommentModel;
import com.libra.cloud.poetry.model.FavoriteModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
public interface FavoriteMapper extends BaseMapper<Favorite> {
    List<FavoriteModel> selectFavoritePage(RowBounds page, @Param("userId") Integer userId);
}
