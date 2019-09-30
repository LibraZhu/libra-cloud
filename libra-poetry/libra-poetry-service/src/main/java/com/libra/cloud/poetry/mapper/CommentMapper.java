package com.libra.cloud.poetry.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.poetry.entity.Comment;
import com.libra.cloud.poetry.model.CommentModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentModel> selectCommentPage(RowBounds page, @Param("poetryId") Integer poetryId, @Param("userId") Integer userId);
}
