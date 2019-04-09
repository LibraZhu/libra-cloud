package com.libra.cloud.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 配置信息表
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "CONFIG_ID", type = IdType.AUTO)
    private Integer configId;
    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 值
     */
    @TableField("VALUES")
    private String values;
    /**
     * 描述
     */
    @TableField("DES")
    private String des;

    /**
     * 类型:1业务类型
     */
    @TableField("TYPE")
    private Integer type;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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
                ", configId=" + configId +
                ", name=" + name +
                ", values=" + values +
                ", des=" + des +
                "}";
    }
}
