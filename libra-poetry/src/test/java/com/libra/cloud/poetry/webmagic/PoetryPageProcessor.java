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
public class PoetryPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String siteUrl = "https://so.gushiwen.org";
    String dynasty;
    String author;

    public PoetryPageProcessor(String author, String dynasty) {
        this.author = author;
        this.dynasty = dynasty;
    }

    @Override
    public void process(Page page) {
        System.out.println("====开始解析诗文信息====");
        String dest = "F:\\poe_poetry.sql";
        String f = "INSERT INTO `poe_poetry` VALUES (NULL, '%s', '%s', '%s', '%s', '%s', %s);";
        StringBuilder sb = new StringBuilder();

        List<Selectable> list = page.getHtml().xpath("//*[@class=\"sons\"]").nodes();
        for (Selectable item : list) {
            String title = item.xpath("//*[@class=\"cont\"]/p/a/b/text()").toString();
            String content = item.xpath("//*[@class=\"cont\"]/[@class=\"contson\"]/html()").toString().replace("\n","");
            List<Selectable> tagList = item.xpath("//*[@class=\"tag\"]/a").nodes();
            String likeNumStr = item.xpath("//*[@class=\"good\"]/a/span/text()").toString();
            int likeNum = 0;
            try {
                if (likeNumStr != null) {
                    likeNumStr = likeNumStr.replace("" + (char) 160, "");
                    likeNum = Integer.valueOf(likeNumStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String tags = null;
            if (tagList != null) {
                StringBuilder tagb = new StringBuilder();
                for (int i = 0; i < tagList.size(); i++) {
                    String tag = tagList.get(i).xpath("//*/text()").toString();
                    if (i < tagList.size() - 1) {
                        tagb.append(tag).append(",");
                    } else {
                        tagb.append(tag);
                    }
                }
                tags = tagb.toString();
            }
            sb.append(String.format(f, title, content, author, dynasty, tags, String.valueOf(likeNum)));
            sb.append("\r\n");
        }
        FileUtil.appendUtf8String(sb.toString(), dest);
        System.out.println("====结束解析诗文信息====");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new PoetryPageProcessor("屈原","先秦")).test("https://so.gushiwen.org/authors/authorvsw_9c69482f885fA1.aspx");
    }
}
