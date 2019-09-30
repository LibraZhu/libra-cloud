package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.CommentReply;
import com.libra.cloud.poetry.mapper.CommentReplyMapper;
import com.libra.cloud.poetry.model.CommentReplyModel;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 回复表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class CommentReplyService extends ServiceImpl<CommentReplyMapper, CommentReply> {

    public PageResult selectReplyPage(Long commentId) {
        Page<CommentReplyModel> page = PageFactory.defaultPage();
        page.setRecords(baseMapper.selectReplyPage(page, commentId));
        return new PageResult<>(page);
    }
}
