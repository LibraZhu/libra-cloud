package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysUserRole;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 用户角色表的初始化
 */
@Component
public class UserRoleInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_user_role` (\n" +
                "  `USER_ROLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
                "  `USER_ID` int(10) NOT NULL COMMENT '用户id',\n" +
                "  `ROLE_ID` int(10) NOT NULL COMMENT '角色id',\n" +
                "  PRIMARY KEY (`USER_ROLE_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';";
    }

    @Override
    public String getTableName() {
        return "sys_user_role";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysUserRole.class;
    }
}
