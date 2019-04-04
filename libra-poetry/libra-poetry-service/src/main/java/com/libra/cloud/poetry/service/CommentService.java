package com.libra.cloud.poetry.service;

import com.libra.cloud.poetry.entity.Comment;
import com.libra.cloud.poetry.mapper.CommentMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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

}
