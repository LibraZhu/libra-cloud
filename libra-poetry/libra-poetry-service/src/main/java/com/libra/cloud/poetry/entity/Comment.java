package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("u_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 诗文id
     */
    @TableField("poetry_id")
    private Integer poetryId;
    /**
     * 诗文标题
     */
    @TableField("poetry_title")
    private String poetryTitle;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户id
     */
    @TableField("from_uid")
    private Integer fromUid;
    /**
     * 点赞数
     */
    @TableField("like_num")
    private Integer likeNum;
    /**
     * 回复数
     */
    @TableField("reply_num")
    private Integer replyNum;
    /**
     * 状态 0:审核; 1:通过; -1:删除
     */
    private Integer status;
    /**
     * 评论时间
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Integer poetryId) {
        this.poetryId = poetryId;
    }

    public String getPoetryTitle() {
        return poetryTitle;
    }

    public void setPoetryTitle(String poetryTitle) {
        this.poetryTitle = poetryTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", id=" + id +
                ", poetryId=" + poetryId +
                ", content=" + content +
                ", fromUid=" + fromUid +
                ", likeNum=" + likeNum +
                ", replyNum=" + replyNum +
                ", status=" + status +
                ", createTime=" + createTime +
                "}";
    }
}
