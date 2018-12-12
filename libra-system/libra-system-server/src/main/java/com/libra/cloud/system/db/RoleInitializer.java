package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysRole;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 角色表的初始化
 */
@Component
public class RoleInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_role` (\n" +
                "  `ROLE_ID` int(10) NOT NULL COMMENT '主键id',\n" +
                "  `NAME` varchar(45) DEFAULT NULL COMMENT '名字',\n" +
                "  `TITLE` varchar(45) DEFAULT NULL COMMENT '标题',\n" +
                "  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',\n" +
                "  `ORDERS` int(11) DEFAULT NULL COMMENT '排序',\n" +
                "  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`ROLE_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';";
    }

    @Override
    public String getTableName() {
        return "sys_role";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysRole.class;
    }
}
