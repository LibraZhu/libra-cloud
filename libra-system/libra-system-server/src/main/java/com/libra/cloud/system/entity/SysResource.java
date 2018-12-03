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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Integer getMenuFlag() {
        return menuFlag;
    }

    public void setMenuFlag(Integer menuFlag) {
        this.menuFlag = menuFlag;
    }

    public Integer getRequiredLogin() {
        return requiredLogin;
    }

    public void setRequiredLogin(Integer requiredLogin) {
        this.requiredLogin = requiredLogin;
    }

    public Integer getRequiredPermission() {
        return requiredPermission;
    }

    public void setRequiredPermission(Integer requiredPermission) {
        this.requiredPermission = requiredPermission;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getModularCode() {
        return modularCode;
    }

    public void setModularCode(String modularCode) {
        this.modularCode = modularCode;
    }

    public String getModularName() {
        return modularName;
    }

    public void setModularName(String modularName) {
        this.modularName = modularName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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
