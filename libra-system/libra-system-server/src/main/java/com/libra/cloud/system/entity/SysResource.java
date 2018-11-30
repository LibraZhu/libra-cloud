package com.libra.cloud.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author libra
 * @since 2018-11-30
 */
@TableName("sys_resource")
public class SysResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源的标识
     */
    @TableId("CODE")
    private String code;
    /**
     * 资源名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 资源的请求路径
     */
    @TableField("URL")
    private String url;
    /**
     * http请求方法
     */
    @TableField("HTTP_METHOD")
    private String httpMethod;
    /**
     * 是否是菜单（1-是，0-否）
     */
    @TableField("MENU_FLAG")
    private Integer menuFlag;
    /**
     * 是否需要登录（1-是，0-否）
     */
    @TableField("REQUIRED_LOGIN")
    private Integer requiredLogin;
    /**
     * 是否需要鉴权（1-是，0-否）
     */
    @TableField("REQUIRED_PERMISSION")
    private Integer requiredPermission;
    /**
     * 应用的标识
     */
    @TableField("APP_CODE")
    private String appCode;
    /**
     * 控制器类名称
     */
    @TableField("CLASS_NAME")
    private String className;
    /**
     * 控制器中的方法名称
     */
    @TableField("METHOD_NAME")
    private String methodName;
    /**
     * 资源所属模块
     */
    @TableField("MODULAR_CODE")
    private String modularCode;
    /**
     * 模块中文名称
     */
    @TableField("MODULAR_NAME")
    private String modularName;
    /**
     * ip地址
     */
    @TableField("IP_ADDRESS")
    private String ipAddress;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;


    @Override
    public String toString() {
        return "SysResource{" +
                ", code=" + code +
                ", name=" + name +
                ", title=" + url +
                ", httpMethod=" + httpMethod +
                ", menuFlag=" + menuFlag +
                ", requiredLogin=" + requiredLogin +
                ", requiredPermission=" + requiredPermission +
                ", appCode=" + appCode +
                ", className=" + className +
                ", methodName=" + methodName +
                ", modularCode=" + modularCode +
                ", modularName=" + modularName +
                ", ipAddress=" + ipAddress +
                "}";
    }
}
