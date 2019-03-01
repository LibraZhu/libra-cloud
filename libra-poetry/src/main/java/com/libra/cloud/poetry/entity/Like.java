package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 点赞表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("u_like")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 点赞对象id
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 点赞类型 1:评论
     */
    private Integer type;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 0:取消点赞   1:点赞
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Like{" +
        ", id=" + id +
        ", typeId=" + typeId +
        ", type=" + type +
        ", uid=" + uid +
        ", status=" + status +
        "}";
    }
}
