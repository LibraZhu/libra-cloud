package com.libra.cloud.poetry.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.poetry.entity.Follow;
import com.libra.cloud.poetry.model.CommentReplyModel;
import com.libra.cloud.poetry.model.FollowModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 关注表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
public interface FollowMapper extends BaseMapper<Follow> {
    List<FollowModel> selectFollowPage(RowBounds page, @Param("uid") Integer uid);

    List<FollowModel> selectFansPage(RowBounds page, @Param("uid") Integer uid);

}
