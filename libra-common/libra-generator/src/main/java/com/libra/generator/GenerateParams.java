package com.libra.generator;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 代码生成所需要传递的参数
 */
@Data
public class GenerateParams {
    //生成代码里，注释的作者
    private String author = "libra";

    //代码生成输出的目录，可为项目路径的相对路径
    private String outputDirectory = "temp";

    //jdbc驱动
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    //数据库连接地址
    private String jdbcUrl = "jdbc:mysql://localhost:3306/libra?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";

    //数据库账号
    private String jdbcUserName = "root";

    //数据库密码
    private String jdbcPassword = "111111";

    //去掉表的前缀
    private String[] removeTablePrefix = {""};

    //代码生成包含的表，可为空，为空默认生成所有
    private String[] includeTables;

    //代码生成的类的父包名称
    private String parentPackage = "com.libra.cloud.system";

    //service是否生成接口，这个根据自己项目情况决定
    private Boolean generatorInterface = false;
}
