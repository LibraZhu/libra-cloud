package com.libra.cloud.poetry.webmagic;

import cn.hutool.core.io.FileUtil;
import us.codecraft.webmagic.Spider;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Libra
 * @date 2018/12/19
 * @description
 */
public class Test {
    public static void main(String[] arg0) {
        String s = "\uD849\uDD9F";
        System.out.println(s);
    }

    public static void replace() {
        String src = "F:\\poe_poetry11.sql";
        String dest = "F:\\poe_poetry.sql";
        List<String> list = FileUtil.readUtf8Lines(src);
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.startsWith("INSERT INTO")) {
                String[] splts = s.split("', '");
                if (splts.length > 2) {
                    String replace = splts[1].replaceAll("'", "");
                    s = s.replace(splts[1], replace);
                    list.set(i, s);
                }
            }
        }
        FileUtil.appendUtf8Lines(list, dest);
    }

    public static void poetry() {
        String src = "F:\\poe_poetry_bak.sql";
        String dest = "F:\\poe_poetry.sql";
        String src_a = "F:\\poe_author_bak.sql";
        String dest_a = "F:\\poe_author.sql";
        String src_d = "F:\\poe_dynasty_bak.sql";
        String dest_d = "F:\\poe_dynasty.sql";
        FileUtil.copy(src, dest, true);
        FileUtil.copy(src_a, dest_a, true);
        FileUtil.copy(src_d, dest_d, true);
        Spider.create(new DynastyPageProcessor()).test("https://so.gushiwen.org/authors/");
    }

}
