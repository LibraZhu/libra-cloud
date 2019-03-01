package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 诗文表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("poe_poetry")
public class Poetry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 诗文id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 诗文标题
     */
    private String title;
    /**
     * 诗文内容
     */
    private String content;
    /**
     * 作者
     */
    private String author;
    /**
     * 朝代
     */
    private String dynasty;
    /**
     * 标签类型
     */
    private String tag;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    @Override
    public String toString() {
        return "Poetry{" +
        ", id=" + id +
        ", title=" + title +
        ", content=" + content +
        ", author=" + author +
        ", dynasty=" + dynasty +
        ", tag=" + tag +
        ", likeNum=" + likeNum +
        "}";
    }
}
