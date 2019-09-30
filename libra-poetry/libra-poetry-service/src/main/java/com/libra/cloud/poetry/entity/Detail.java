package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author libra
 * @since 2019-06-19
 */
@TableName("poe_detail")
public class Detail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 诗文id
     */
    @TableField("poetry_id")
    private Integer poetryId;
    /**
     * 译文
     */
    private String translation;
    /**
     * 注释
     */
    private String remark;
    /**
     * 赏析
     */
    private String appreciate;
    /**
     * 音频链接
     */
    @TableField("audio_url")
    private String audioUrl;


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

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAppreciate() {
        return appreciate;
    }

    public void setAppreciate(String appreciate) {
        this.appreciate = appreciate;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @Override
    public String toString() {
        return "Detail{" +
        ", id=" + id +
        ", poetryId=" + poetryId +
        ", translation=" + translation +
        ", remark=" + remark +
        ", appreciate=" + appreciate +
        ", audioUrl=" + audioUrl +
        "}";
    }
}
