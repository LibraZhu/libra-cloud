package com.libra.cloud.poetry.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.net.URLDecoder;
import java.util.List;

/**
 * @author Libra
 * @date 2018/12/21
 * @description
 */
public class AuthorPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String siteUrl = "https://so.gushiwen.org";
    private boolean root = false;

    public AuthorPageProcessor(boolean root) {
        this.root = root;
    }

    @Override
    public void process(Page page) {
        if (root) {
            System.out.println("====开始解析朝代下的作者====");
            Selectable selectable = page.getHtml().xpath("//*[@class=\"titletype\"]");
            String num = selectable.xpath("//*h1/span/text()").toString().replace("1 / ", "").replace("页", "");
            int size = Integer.valueOf(num);
            String[] urls = new String[size];
            for (int i = 0; i < size; i++) {
                int p = i + 1;
                urls[i] = page.getUrl().get().replace("p=1", "p=" + p);
            }
            System.out.println("====结束解析朝代下的作者====" + urls.length + "位");
            Spider.create(new AuthorPageProcessor(false)).test(urls);
        } else {
            System.out.println("====开始解析作者====");
            List<Selectable> list = page.getHtml().xpath("//*/[@class=\"sonspic\"]").nodes();
            String[] urls = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                urls[i] = siteUrl + list.get(i).xpath("//*[@style=\"font-size:18px; line-height:22px; height:22px;\"]/@href").toString();
            }
            System.out.println("====结束解析作者====");
            String url = page.getUrl().get();
            String dynasty = URLDecoder.decode(url.substring(url.indexOf("c=") + 2));
            Spider.create(new AuthorInfoPageProcessor(dynasty)).test(urls);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AuthorPageProcessor(false)).test("https://so.gushiwen.org/authors/Default.aspx?p=77&c=%E5%94%90%E4%BB%A3");
    }
}
