package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysUser;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 用户表的初始化
 */
@Component
public class UserInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_user` (\n" +
                "  `USER_ID` int(10) NOT NULL COMMENT '主键id',\n" +
                "  `AVATAR` varchar(255) DEFAULT NULL COMMENT '头像',\n" +
                "  `ACCOUNT` varchar(45) DEFAULT NULL COMMENT '账号',\n" +
                "  `PASSWORD` varchar(45) DEFAULT NULL COMMENT '密码',\n" +
                "  `SALT` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',\n" +
                "  `NAME` varchar(45) DEFAULT NULL COMMENT '名字',\n" +
                "  `BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',\n" +
                "  `SEX` char(1) DEFAULT NULL COMMENT '性别（M：男 F：女）',\n" +
                "  `EMAIL` varchar(45) DEFAULT NULL COMMENT '电子邮件',\n" +
                "  `PHONE` varchar(45) DEFAULT NULL COMMENT '电话',\n" +
                "  `STATUS` int(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',\n" +
                "  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                "  PRIMARY KEY (`USER_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';";
    }

    @Override
    public String getTableName() {
        return "sys_user";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysUser.class;
    }
}
