package com.libra.cloud.poetry.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.libra.cloud.poetry.entity.CommentReply;
import com.libra.cloud.poetry.model.CommentReplyModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * <p>
 * 回复表 Mapper 接口
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
public interface CommentReplyMapper extends BaseMapper<CommentReply> {
    List<CommentReplyModel> selectReplyPage(RowBounds page, @Param("commentId") Long commentId);
}
