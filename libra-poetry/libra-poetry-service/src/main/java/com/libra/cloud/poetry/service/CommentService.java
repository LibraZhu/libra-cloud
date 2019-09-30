package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Comment;
import com.libra.cloud.poetry.mapper.CommentMapper;
import com.libra.cloud.poetry.model.CommentModel;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    /**
     * 获取最新评论列表
     *
     * @return 评论列表
     */
    public PageResult selectCommentPage(Integer poetryId, Integer userId) {
        Page<CommentModel> page = PageFactory.defaultPage();
        page.setRecords(baseMapper.selectCommentPage(page, poetryId, userId));
        return new PageResult<>(page);
    }

    /**
     * 获取诗词的评论数
     *
     * @param poetryId 诗词id
     * @return 评论数
     */
    public int count(Integer poetryId) {
        return selectCount(new EntityWrapper<Comment>().eq("poetry_id", poetryId));
    }
}
