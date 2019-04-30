package com.libra.cloud.system.db;

import com.libra.cloud.system.entity.SysAd;
import com.libra.cloud.system.entity.SysConfig;
import com.libra.core.db.DbInitializer;
import org.springframework.stereotype.Component;

/**
 * @author Libra
 * @date 2018/11/29
 * @description 广告信息表的初始化
 */
@Component
public class AdInitializer extends DbInitializer {
    @Override
    public String getTableInitSql() {
        return "CREATE TABLE `sys_ad` (\n" +
                "  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',\n" +
                "  `TITLE` varchar(255) DEFAULT NULL COMMENT '名称',\n" +
                "  `IMAGE` varchar(255) DEFAULT NULL COMMENT '图片',\n" +
                "  `URL` varchar(255) DEFAULT NULL COMMENT '链接',\n" +
                "  `DES` varchar(255) DEFAULT NULL COMMENT '描述',\n" +
                "  `STATUS` int(11) DEFAULT NULL COMMENT '状态',\n" +
                "  `TYPE` int(11) DEFAULT NULL COMMENT '类型',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告信息表';";
    }

    @Override
    public String getTableName() {
        return "sys_ad";
    }

    @Override
    public Class<?> getEntityClass() {
        return SysAd.class;
    }
}
