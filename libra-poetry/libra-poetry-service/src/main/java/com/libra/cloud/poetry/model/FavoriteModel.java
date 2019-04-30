package com.libra.cloud.poetry.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author libra
 * @since 2018-12-27
 */
@Data
public class FavoriteModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;
    /**
     * 诗文id
     */
    private Integer poetryId;
    private String poetryTitle;
    private String poetryAuthor;
    private String poetryContent;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 收藏时间
     */
    private Date createTime;
    /**
     * 状态 0:取消收藏; 1:收藏
     */
    private Integer status;
}
