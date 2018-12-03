package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysRoleResource;
import com.libra.cloud.system.entity.SysUserRole;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 角色资源表的初始化
 */
@Component
public class RoleResourceInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_role_resource` (\n" +
                "  `ROLE_RESOURCE_ID` int(10) NOT NULL COMMENT '主键id',\n" +
                "  `ROLE_ID` int(10) NOT NULL COMMENT '角色id',\n" +
                "  `CODE` varchar(45) NOT NULL COMMENT '资源的标识',\n" +
                "  PRIMARY KEY (`ROLE_RESOURCE_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';";
    }

    @Override
    public String getTableName() {
        return "sys_role_resource";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysRoleResource.class;
    }
}
