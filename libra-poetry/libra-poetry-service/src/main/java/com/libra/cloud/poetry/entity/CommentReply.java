package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 回复表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("u_comment_reply")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 评论id
     */
    @TableField("comment_id")
    private Long commentId;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 回复类型 1:针对评论的回复; 2:针对回复的回复
     */
    @TableField("reply_type")
    private Integer replyType;
    /**
     * 被回复的id reply_type=1为comment_id;reply_type=2为回复表的id
     */
    @TableField("reply_id")
    private Long replyId;
    /**
     * 回复者id
     */
    @TableField("from_uid")
    private Integer fromUid;
    /**
     * 被回复者id
     */
    @TableField("to_uid")
    private Integer toUid;
    /**
     * 回复时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CommentReply{" +
        ", id=" + id +
        ", commentId=" + commentId +
        ", content=" + content +
        ", replyType=" + replyType +
        ", replyId=" + replyId +
        ", fromUid=" + fromUid +
        ", toUid=" + toUid +
        ", createTime=" + createTime +
        "}";
    }
}
