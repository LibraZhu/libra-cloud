package com.libra.cloud.poetry.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 朝代表
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@TableName("poe_dynasty")
public class Dynasty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 朝代id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 朝代
     */
    private String name;


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

    @Override
    public String toString() {
        return "Dynasty{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
