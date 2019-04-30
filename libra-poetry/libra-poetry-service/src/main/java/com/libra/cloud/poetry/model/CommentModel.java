package com.libra.cloud.poetry.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Libra
 * @date 2019/4/17
 * @description
 */
@Data
public class CommentModel {
    /**
     * 编号
     */
    private Long id;
    /**
     * 诗文id
     */
    private Integer poetryId;
    /**
     * 诗文标题
     */
    private String poetryTitle;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户id,名字,头像
     */
    private Integer fromUid;
    private String fromUserName;
    private String fromUserAvatar;
    /**
     * 点赞数
     */
    private Integer likeNum;
    /**
     * 回复数
     */
    private Integer replyNum;
    /**
     * 状态 0:审核; 1:通过; -1:删除
     */
    private Integer status;
    /**
     * 评论时间
     */
    private Date createTime;
}
