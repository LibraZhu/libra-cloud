package com.libra.cloud.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 广告信息表
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
@TableName("sys_ad")
public class SysAd implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    @TableField("TITLE")
    private String title;
    /**
     * 图片
     */
    @TableField("IMAGE")
    private String image;
    /**
     * 描述
     */
    @TableField("DES")
    private String des;
    /**
     * 链接
     */
    @TableField("URL")
    private String url;
    /**
     * 状态 1:在线 0:下线
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 类型:1:banner
     */
    @TableField("TYPE")
    private Integer type;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                ", id=" + id +
                ", title=" + title +
                ", image=" + image +
                ", des=" + des +
                ", url=" + url +
                ", status=" + status +
                ", type=" + type +
                "}";
    }
}
