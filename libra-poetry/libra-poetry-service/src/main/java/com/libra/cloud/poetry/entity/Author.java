package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 作者表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("poe_author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作者id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 头像
     */
    private String image;
    /**
     * 介绍
     */
    private String des;
    /**
     * 朝代
     */
    private String dynasty;
    /**
     * 诗文数量
     */
    @TableField("poetry_num")
    private Integer poetryNum;
    /**
     * 点赞数
     */
    @TableField("like_num")
    private Integer likeNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public Integer getPoetryNum() {
        return poetryNum;
    }

    public void setPoetryNum(Integer poetryNum) {
        this.poetryNum = poetryNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "Author{" +
        ", id=" + id +
        ", name=" + name +
        ", image=" + image +
        ", des=" + des +
        ", dynasty=" + dynasty +
        ", poetryNum=" + poetryNum +
        ", likeNum=" + likeNum +
        "}";
    }
}
