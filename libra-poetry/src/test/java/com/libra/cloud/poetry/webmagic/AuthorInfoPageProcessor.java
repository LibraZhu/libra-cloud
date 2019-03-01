package com.libra.cloud.poetry.webmagic;

import cn.hutool.core.io.FileUtil;
import com.libra.cloud.poetry.webmagic.bean.Author;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author Libra
 * @date 2018/12/21
 * @description
 */
public class AuthorInfoPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String siteUrl = "https://so.gushiwen.org";
    String dynasty;

    public AuthorInfoPageProcessor(String dynasty) {
        this.dynasty = dynasty;
    }

    @Override
    public void process(Page page) {
        System.out.println("====开始解析作者信息====");
        Selectable item = page.getHtml().xpath("//*[@class=\"left\"]/[@class=\"sonspic\"]");
        String image = item.xpath("//*[@class=\"divimg\"]/img/@src").toString();
        String name = item.xpath("//*[@style=\"font-size:20px; line-height:22px; height:22px;\"]/b/text()").toString();
        String desc = item.xpath("//*[@style=\" margin:0px;\"]/text()").toString();
        String numStr = item.xpath("//*[@style=\" margin:0px;\"]/a/text()").toString();
        String url = siteUrl + item.xpath("//*[@style=\" margin:0px;\"]/a/@href").toString();
        String likeNumStr = item.xpath("//*[@class=\"good\"]/a/span/text()").toString();
        int size = 0;
        int likeNum = 0;
        try {
            if (numStr != null) {
                numStr = numStr.replace("► ", "").replace("篇诗文", "");
                size = Integer.valueOf(numStr);
            }
            if (likeNumStr != null) {
                likeNumStr = likeNumStr.replace("" + (char) 160, "");
                likeNum = Integer.valueOf(likeNumStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(image + "," + name + "," + desc + "," + size + "," + likeNum);
        Author author = new Author();
        author.setName(name);
        author.setImage(image);
        author.setDesc(desc);
        author.setPoetryNum(size);
        author.setLikeNum(likeNum);
        author.setDynasty(dynasty);
        author.setPoetryUrl(url);
        String dest = "F:\\poe_author.sql";
        String f = "INSERT INTO `poe_author` VALUES (NULL, '%s', '%s', '%s', '%s', %s, %s);";
        String sb = String.format(f, name, image, desc, dynasty, String.valueOf(size), String.valueOf(likeNum)) +
                "\r\n";
        FileUtil.appendUtf8String(sb, dest);
        System.out.println("====结束解析作者信息====");
        int pageNum;
        if (size % 10 == 0) {
            pageNum = size / 10;
        } else {
            pageNum = size / 10 + 1;
        }
        if (pageNum > 0) {
            String[] urls = new String[pageNum];
            for (int i = 0; i < pageNum; i++) {
                int p = i + 1;
                urls[i] = url.replace("A1", "A" + p);
            }
            Spider.create(new PoetryPageProcessor(name, dynasty)).test(urls);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
