package com.libra.cloud.poetry.controller;

import com.libra.cloud.poetry.entity.CommentReply;
import com.libra.cloud.poetry.service.CommentReplyService;
import com.libra.core.constants.Constants;
import com.libra.core.jwt.utils.JwtTokenUtil;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(name = "评论回复管理", path = "/poetry/reply")
public class CommentReplyController {

    @Autowired
    CommentReplyService commentReplyService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(name = "获取最新评论列表", path = "/list")
    public ResponseData getCommentPage(RequestData requestData) {
        return ResponseData.success(commentReplyService.selectReplyPage(requestData.getParam().getLong("commentId")));
    }


    @PostMapping(name = "添加评论回复", path = "/add")
    public ResponseData add(RequestData requestData) {
        Integer toUid = requestData.getParam().getInteger("toUid");
        Long commentId = requestData.getParam().getLong("commentId");
        String content = requestData.getParam().getString("content");
        CommentReply commentReply = new CommentReply();
        commentReply.setCommentId(commentId);
        commentReply.setContent(content);
        commentReply.setReplyType(1);
        commentReply.setReplyId(commentId);
        commentReply.setToUid(toUid);
        int userId = Integer.valueOf(jwtTokenUtil.getUserIdFromToken(requestData.getString(Constants.REQUEST_TOKEN_PARAM)));
        commentReply.setFromUid(userId);
        commentReply.setCreateTime(new Date());
        commentReplyService.insertAllColumn(commentReply);
        return ResponseData.success(commentReply);
    }
}
