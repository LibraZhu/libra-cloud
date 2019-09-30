package com.libra.cloud.poetry.controller;

import com.libra.cloud.poetry.entity.Comment;
import com.libra.cloud.poetry.entity.Like;
import com.libra.cloud.poetry.service.CommentService;
import com.libra.cloud.poetry.service.LikeService;
import com.libra.core.constants.Constants;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Libra
 * @date 2018/12/7
 * @description
 */
@RestController
@RequestMapping(name = "评论管理", path = "/poetry/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(name = "获取评论数", path = "/count")
    public ResponseData count(RequestData requestData) {
        return ResponseData.success(commentService.count(requestData.getParam().getInteger("poetryId")));
    }

    @PostMapping(name = "获取最新评论列表", path = "/list")
    public ResponseData getCommentPage(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        return ResponseData.success(commentService.selectCommentPage(requestData.getParam().getInteger("poetryId"), userId));
    }

    @PostMapping(name = "添加评论", path = "/add")
    public ResponseData add(RequestData requestData) {
        Comment comment = new Comment();
        comment.setPoetryId(requestData.getParam().getInteger("poetryId"));
        comment.setPoetryTitle(requestData.getParam().getString("poetryTitle"));
        comment.setContent(requestData.getParam().getString("content"));

        int userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        comment.setFromUid(userId);
        comment.setStatus(1);
        comment.setCreateTime(new Date());
        commentService.insertAllColumn(comment);
        return ResponseData.success(comment);
    }

    @PostMapping(name = "删除评论", path = "/delete")
    public ResponseData delete(RequestData requestData) {
        commentService.deleteById(requestData.getParam().getString("commentId"));
        return ResponseData.success();
    }

    @PostMapping(name = "点赞评论", path = "/like")
    public ResponseData like(RequestData requestData) {
        Integer userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        Integer commentId = requestData.getParam().getInteger("commentId");
        Like like = likeService.selectCommentLike(userId, commentId.longValue());
        if (like == null) {
            like = new Like();
            like.setType(1);
            like.setUid(userId);
            like.setTypeId(commentId.longValue());
        }
        like.setStatus(1 - like.getStatus());
        likeService.insertOrUpdate(like);
        Comment comment = commentService.selectById(commentId);
        if (like.getStatus() == 1) {
            comment.setLikeNum(comment.getLikeNum() + 1);
        } else {
            comment.setLikeNum(comment.getLikeNum() - 1);
        }
        commentService.insertOrUpdate(comment);
        return ResponseData.success(like.getStatus() == 1);
    }
}
