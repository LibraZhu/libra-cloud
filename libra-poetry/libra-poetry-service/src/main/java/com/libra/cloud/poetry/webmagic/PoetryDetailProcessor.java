package com.libra.cloud.poetry.webmagic;

import com.libra.cloud.poetry.entity.Detail;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class PoetryDetailProcessor {
    private static final String www = "https://www.gushiwen.org";
    private static final String so = "https://so.gushiwen.org";

    private String urlPath;

    public PoetryDetailProcessor() {
        urlPath = so;
    }

    public Detail parse(String title, String author, int page) {
        if (page == 0) {
            page = 1;
        }
        Detail detail = new Detail();
        long startTime = System.currentTimeMillis();
        try {
            Document document = Jsoup.connect("https://so.gushiwen.org/search.aspx?value=" + title + "&page=" + page).ignoreContentType(true).get();
            Elements ids = document.select("img[id~=btnBeisong+]");
            Elements sources = document.select("p[class=source]");
            String pid = null;
            for (int i = 0; i < ids.size(); i++) {
                Element id = ids.get(i);
                Element source = sources.get(i);
                List<String> sourceList = source.children().eachText();
                if (sourceList.contains(author)) {
                    pid = id.attr("id").replace("btnBeisong", "");
                    break;
                }
            }
            if (pid != null && !pid.isEmpty()) {
                String url = "https://so.gushiwen.org/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=yizhushang";
                System.out.println(url);
                Document doc = Jsoup.connect(url).ignoreContentType(true).get();
                String yi = "";
                String zhu = "";
                String shang = "";
                Elements yizhushangs = doc.getElementsByTag("p");
                if (yizhushangs.size() > 0) {
                    for (Element yizhushang : yizhushangs) {
                        Elements yizhus = yizhushang.children();
                        if (yizhus.size() > 0) {
                            for (int i = 0; i < yizhus.size(); i++) {
                                Element yizhu = yizhus.get(i);
                                Elements children = yizhu.getElementsByAttributeValue("style", "color:#76621c;");
                                for (Element child : children) {
                                    yi += child.text() + "\n";
                                }
                                children = yizhu.getElementsByAttributeValue("style", "color:#286345;");
                                for (Element child : children) {
                                    if (child.text().startsWith("(") && child.text().endsWith(")")) {
                                        continue;
                                    }
                                    zhu += child.text() + "\n";
                                }
                            }
                        } else if (yizhushang.hasAttr("style")) {
                            continue;
                        } else {
                            shang += yizhushang.text() + "\n";
                        }
                    }
                    detail.setTranslation(yi);
                    detail.setRemark(zhu);
                    detail.setAppreciate(shang);
                    System.out.println(detail.getTranslation());
                    System.out.println(detail.getRemark());
                    System.out.println(detail.getAppreciate());
                } else {
                    parseOther(pid, detail);
                    if (StringUtils.isEmpty(detail.getTranslation()) ||
                            StringUtils.isEmpty(detail.getRemark()) ||
                            StringUtils.isEmpty(detail.getAppreciate())) {
                        urlPath = www;
                        parseOther(pid, detail);
                    }
                }
            } else {
                //去下一页找
                Integer temppage = Integer.valueOf(document.select("label[id=temppage]").text());
                Integer sumPage = Integer.valueOf(document.select("label[id=sumPage]").text());
                if (sumPage > temppage) {
                    detail = parse(title, author, temppage + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        return detail;
    }

    private void parseOther(String pid, Detail detail) {
        parseYiZhu(pid, detail);
        if (StringUtils.isEmpty(detail.getTranslation())) {
            parseYi(pid, detail);
        }
        if (StringUtils.isEmpty(detail.getRemark())) {
            parseZhu(pid, detail);
        }
        parseShang(pid, detail);

        if (StringUtils.isEmpty(detail.getTranslation())) {
            parseYiShang(pid, detail);
        }
        if (StringUtils.isEmpty(detail.getRemark())) {
            parseZhuShang(pid, detail);
        }
        System.out.println(detail.toString());
    }

    private void parseYi(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=yi";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String yi = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    for (int i = 0; i < yizhus.size(); i++) {
                        Element yizhu = yizhus.get(i);
                        Elements children = yizhu.getElementsByAttributeValue("style", "color:#76621c;");
                        for (Element child : children) {
                            yi += child.text() + "\n";
                        }
                    }
                }
            }
            if (!StringUtils.isEmpty(yi)) {
                detail.setTranslation(yi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseZhu(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=zhu";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String zhu = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    for (int i = 0; i < yizhus.size(); i++) {
                        Element yizhu = yizhus.get(i);
                        Elements children = yizhu.getElementsByAttributeValue("style", "color:#286345;");
                        for (Element child : children) {
                            if (child.text().startsWith("(") && child.text().endsWith(")")) {
                                continue;
                            }
                            zhu += child.text() + "\n";
                        }
                    }
                }
            }
            if (!StringUtils.isEmpty(zhu)) {
                detail.setRemark(zhu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseShang(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=shang";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String shang = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    continue;
                } else if (yizhushang.hasAttr("style")) {
                    continue;
                } else {
                    shang += yizhushang.text() + "\n";
                }
            }
            detail.setAppreciate(shang);
            if (!StringUtils.isEmpty(shang)) {
                detail.setAppreciate(shang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void parseYiZhu(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=yizhu";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String yi = "";
            String zhu = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    for (int i = 0; i < yizhus.size(); i++) {
                        Element yizhu = yizhus.get(i);
                        Elements children = yizhu.getElementsByAttributeValue("style", "color:#76621c;");
                        for (Element child : children) {
                            yi += child.text() + "\n";
                        }
                        children = yizhu.getElementsByAttributeValue("style", "color:#286345;");
                        for (Element child : children) {
                            if (child.text().startsWith("(") && child.text().endsWith(")")) {
                                continue;
                            }
                            zhu += child.text() + "\n";
                        }
                    }
                }
            }
            if (!StringUtils.isEmpty(yi)) {
                detail.setTranslation(yi);
            }
            if (!StringUtils.isEmpty(zhu)) {
                detail.setRemark(zhu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseYiShang(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=yishang";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String yi = "";
            String shang = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    for (int i = 0; i < yizhus.size(); i++) {
                        Element yizhu = yizhus.get(i);
                        Elements children = yizhu.getElementsByAttributeValue("style", "color:#76621c;");
                        for (Element child : children) {
                            yi += child.text() + "\n";
                        }
                    }
                } else if (yizhushang.hasAttr("style")) {
                    continue;
                } else {
                    shang += yizhushang.text() + "\n";
                }
            }
            if (!StringUtils.isEmpty(yi)) {
                detail.setTranslation(yi);
            }
            if (!StringUtils.isEmpty(shang)) {
                detail.setAppreciate(shang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseZhuShang(String pid, Detail detail) {
        try {
            String url = urlPath + "/shiwen2017/ajaxshiwencont.aspx?id=" + pid + "&value=zhushang";
            System.out.println(url);
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            String zhu = "";
            String shang = "";
            Elements yizhushangs = doc.getElementsByTag("p");
            for (Element yizhushang : yizhushangs) {
                Elements yizhus = yizhushang.children();
                if (yizhus.size() > 0) {
                    for (int i = 0; i < yizhus.size(); i++) {
                        Element yizhu = yizhus.get(i);
                        Elements children = yizhu.getElementsByAttributeValue("style", "color:#286345;");
                        for (Element child : children) {
                            if (child.text().startsWith("(") && child.text().endsWith(")")) {
                                continue;
                            }
                            zhu += child.text() + "\n";
                        }
                    }
                } else if (yizhushang.hasAttr("style")) {
                    continue;
                } else {
                    shang += yizhushang.text() + "\n";
                }
            }
            if (!StringUtils.isEmpty(zhu)) {
                detail.setRemark(zhu);
            }
            if (!StringUtils.isEmpty(shang)) {
                detail.setAppreciate(shang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
