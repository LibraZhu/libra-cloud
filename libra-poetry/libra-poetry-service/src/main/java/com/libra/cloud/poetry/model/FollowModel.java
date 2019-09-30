package com.libra.cloud.poetry.model;

import lombok.Data;

import java.util.Date;

@Data
public class FollowModel {
    /**
     * 编号
     */
    private Long id;
    /**
     * 用户id
     */
    private Integer uid;
    private String nickName;
    private String avatar;
    /**
     * 被关注者id
     */
    private Integer followedId;
    private String followedNickName;
    private String followedAvatar;
    /**
     * 状态: 0:取消关注; 1:关注
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 粉丝的uid，如果uidFans有值说明互相关注了
     */
    private Integer uidFans;
}
