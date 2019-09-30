package com.libra.cloud.poetry.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Libra
 * @date 2019/4/17
 * @description
 */
@Data
public class CommentReplyModel {
    /**
     * 编号
     */
    private Long id;
    /**
     * 评论id
     */
    private String commentId;
    /**
     * 内容
     */
    private String content;
    /**
     * 回复类型 1:针对评论的回复; 2:针对回复的回复
     */
    private Integer replyType;
    /**
     * 被回复的id reply_type=1为comment_id;reply_type=2为回复表的id
     */
    private Long replyId;
    /**
     * 用户id,名字,头像
     */
    private Integer fromUid;
    private String fromUserName;
    private String fromUserAvatar;
    /**
     * 被回复者id
     */
    private Integer toUid;
    /**
     * 评论时间
     */
    private Date createTime;
}
