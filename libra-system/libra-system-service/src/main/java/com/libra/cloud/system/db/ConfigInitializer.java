package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysConfig;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 配置信息表的初始化
 */
@Component
public class ConfigInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_config` (\n" +
                "  `CONFIG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
                "  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',\n" +
                "  `VALUES` varchar(255) DEFAULT NULL COMMENT '值',\n" +
                "  `DES` varchar(255) DEFAULT NULL COMMENT '描述',\n" +
                "  `TYPE` int(11) DEFAULT NULL COMMENT '类型',\n" +
                "  PRIMARY KEY (`CONFIG_ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置信息表';";
    }

    @Override
    public String getTableName() {
        return "sys_config";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysConfig.class;
    }
}
