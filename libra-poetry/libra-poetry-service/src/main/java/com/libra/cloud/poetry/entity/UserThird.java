package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 第三方登录表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("u_user_third")
public class UserThird implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 第三方唯一标识
     */
    @TableField("third_key")
    private String thirdKey;
    /**
     * 第三发类型 1:微信;2:QQ
     */
    @TableField("third_type")
    private Integer thirdType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }

    public Integer getThirdType() {
        return thirdType;
    }

    public void setThirdType(Integer thirdType) {
        this.thirdType = thirdType;
    }

    @Override
    public String toString() {
        return "UserThird{" +
        ", id=" + id +
        ", uid=" + uid +
        ", thirdKey=" + thirdKey +
        ", thirdType=" + thirdType +
        "}";
    }
}
