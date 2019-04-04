package com.libra.cloud.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 角色-资源关联表
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
@TableName("sys_role_resource")
public class SysRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "ROLE_RESOURCE_ID", type = IdType.AUTO)
    private Integer roleResourceId;
    /**
     * 角色编号
     */
    @TableField("ROLE_ID")
    private Integer roleId;
    /**
     * 资源的标识
     */
    @TableField("CODE")
    private String code;


    public Integer getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(Integer roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                ", roleResourceId=" + roleResourceId +
                ", code=" + code +
                ", roleId=" + roleId +
                "}";
    }
}
