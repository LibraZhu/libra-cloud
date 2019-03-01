package com.libra.cloud.poetry;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author Libra
 * @date 2018/12/27
 * @description
 */
public class Test {
    public static void main(String[] arg0) {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(DateUtil.parseDate(DateUtil.today()).getTime());
    }
}
