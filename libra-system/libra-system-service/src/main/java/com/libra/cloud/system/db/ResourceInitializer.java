package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysResource;
import com.libra.cloud.system.entity.SysRole;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 资源表的初始化
 */
@Component
public class ResourceInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_resource` (\n" +
                "  `CODE` varchar(45) NOT NULL COMMENT '资源的标识',\n" +
                "  `NAME` varchar(45) DEFAULT NULL COMMENT '资源名称',\n" +
                "  `URL` varchar(45) DEFAULT NULL COMMENT '资源的请求路径',\n" +
                "  `HTTP_METHOD` varchar(45) DEFAULT NULL COMMENT 'http请求方法',\n" +
                "  `MENU_FLAG` int(11) DEFAULT NULL COMMENT '是否是菜单（1-是，0-否）',\n" +
                "  `REQUIRED_LOGIN` int(11) DEFAULT NULL COMMENT '是否需要登录（1-是，0-否）',\n" +
                "  `REQUIRED_PERMISSION` int(11) DEFAULT NULL COMMENT '是否需要鉴权（1-是，0-否）',\n" +
                "  `APP_CODE` varchar(45) DEFAULT NULL COMMENT '应用的标识',\n" +
                "  `CLASS_NAME` varchar(45) DEFAULT NULL COMMENT '控制器类名称',\n" +
                "  `METHOD_NAME` varchar(45) DEFAULT NULL COMMENT '控制器中的方法名称',\n" +
                "  `MODULAR_CODE` varchar(45) DEFAULT NULL COMMENT '资源所属模块',\n" +
                "  `MODULAR_NAME` varchar(45) DEFAULT NULL COMMENT '模块中文名称',\n" +
                "  `IP_ADDRESS` varchar(45) DEFAULT NULL COMMENT 'ip地址',\n" +
                "  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`CODE`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';";
    }

    @Override
    public String getTableName() {
        return "sys_resource";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysResource.class;
    }
}
