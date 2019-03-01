package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("u_favorite")
public class Favorite implements Serializable {

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
     * 用户id
     */
    private Integer uid;
    /**
     * 收藏时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 状态 0:取消收藏; 1:收藏
     */
    private Integer status;


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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Favorite{" +
        ", id=" + id +
        ", poetryId=" + poetryId +
        ", uid=" + uid +
        ", createTime=" + createTime +
        ", status=" + status +
        "}";
    }
}
