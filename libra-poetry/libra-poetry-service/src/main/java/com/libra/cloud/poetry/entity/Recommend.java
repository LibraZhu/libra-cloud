package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 推荐表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("poe_recommend")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 诗文id
     */
    @TableField("poetry_id")
    private Integer poetryId;
    /**
     * 推荐日期
     */
    private Date time;
    /**
     * 类型 0:原诗文; 1:用户诗文
     */
    private Integer type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Integer poetryId) {
        this.poetryId = poetryId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Recommend{" +
        ", id=" + id +
        ", poetryId=" + poetryId +
        ", time=" + time +
        ", type=" + type +
        "}";
    }
}
