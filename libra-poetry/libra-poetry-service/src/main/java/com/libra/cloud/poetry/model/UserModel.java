package com.libra.cloud.poetry.model;

import com.libra.cloud.poetry.entity.UserThird;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Libra
 * @date 2019/3/25
 * @description
 */
@Data
public class UserModel {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 鐢熸棩
     */
    private Date birthday;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别 1:男;2:女
     */
    private Integer sex;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态 1:正常;2冻结
     */
    private Integer status;
    /**
     * ip鍦板潃
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * token
     */
    private String token;

    /**
     * 第三方信息
     */
    private List<UserThird> thirdInfoList;

    private Integer follow;

    private Integer fans;
}
