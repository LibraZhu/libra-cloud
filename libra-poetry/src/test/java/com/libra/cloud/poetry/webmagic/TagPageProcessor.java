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
public class TagPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public TagPageProcessor() {
    }

    @Override
    public void process(Page page) {
        System.out.println("====开始解析标签类型====");
        String src = "F:\\poe_tag_bak.sql";
        String dest = "F:\\poe_tag.sql";
        FileUtil.copy(src, dest, true);
        String f = "INSERT INTO `poe_tag` VALUES (NULL, '%s',1);";
        StringBuilder sb = new StringBuilder();
        List<Selectable> items = page.getHtml().xpath("//*[@id=\"type1\"]/[@class=\"sright\"]/a").nodes();
        for (int i = 0; i < items.size(); i++) {
            Selectable selectable = items.get(i);
            String name = selectable.xpath("//*/text()").toString();
            sb.append(String.format(f, name));
            sb.append("\r\n");
        }
        FileUtil.appendUtf8String(sb.toString(), dest);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TagPageProcessor()).test("https://www.gushiwen.org/shiwen/");
    }
}
