package com.libra.cloud.poetry.webmagic;

import cn.hutool.core.io.FileUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/21
 * @description
 */
public class DynastyPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String siteUrl = "https://so.gushiwen.org";

    @Override
    public void process(Page page) {
        System.out.println("====开始解析朝代====");
        String dest = "F:\\poe_dynasty.sql";
        String f = "INSERT INTO `poe_dynasty` VALUES (NULL, '%s');";
        StringBuilder sb = new StringBuilder();

        List<Selectable> list = page.getHtml().xpath("//*[@class=\"sright\"]/a").nodes();
        String[] urls = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String url = siteUrl + list.get(i).xpath("//*/@href").toString();
            String name = list.get(i).xpath("//*/text()").toString();
            urls[i] = url;

            sb.append(String.format(f, name));
            sb.append("\r\n");
        }
        FileUtil.appendUtf8String(sb.toString(), dest);
        System.out.println("====结束解析朝代====" + urls.length + "个");
        Spider.create(new AuthorPageProcessor(true)).test(urls);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DynastyPageProcessor()).test("https://so.gushiwen.org/authors/");
    }
}
