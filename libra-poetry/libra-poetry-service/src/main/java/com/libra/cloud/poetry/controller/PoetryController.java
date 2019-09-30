package com.libra.cloud.poetry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.libra.cloud.poetry.entity.Detail;
import com.libra.cloud.poetry.entity.Poetry;
import com.libra.cloud.poetry.service.*;
import com.libra.cloud.poetry.webmagic.PoetryDetailProcessor;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/20
 * @description
 */
@RestController
@RequestMapping(name = "诗文管理", path = "/poetry")
public class PoetryController {
    @Autowired
    PoetryService poetryService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;
    @Autowired
    DynastyService dynastyService;
    @Autowired
    RecommendService recommendService;
    @Autowired
    DetailService detailService;

    @PostMapping(name = "搜索列表", path = "/search")
    public ResponseData search(RequestData requestData) {
        return ResponseData.success(poetryService.search(requestData.getParam().getString("keyword")));
    }

    @PostMapping(name = "诗文列表", path = "/list")
    public ResponseData getPoetryList(RequestData requestData) {
        return ResponseData.success(poetryService.selectListPage());
    }

    @PostMapping(name = "诗文列表", path = "/listByAuthor")
    public ResponseData getPoetryListByAuthor(RequestData requestData) {
        return ResponseData.success(poetryService.selectListPageByAuthor(requestData.getParam().getString("author")));
    }

    @PostMapping(name = "诗文列表", path = "/listByDynasty")
    public ResponseData getPoetryListByDynasty(RequestData requestData) {
        return ResponseData.success(poetryService.selectListPageByDynasty(requestData.getParam().getString("dynasty")));
    }

    @PostMapping(name = "诗文列表", path = "/listByTag")
    public ResponseData getPoetryListByTag(RequestData requestData) {
        return ResponseData.success(poetryService.selectListPageByTag(requestData.getParam().getString("tag")));
    }

    @PostMapping(name = "诗文信息", path = "/info")
    public ResponseData getPoetryInfo(RequestData requestData) {
        Integer id = requestData.getParam().getInteger("poetryId");
        return ResponseData.success(poetryService.selectPoetryInfoById(id));
    }

    @PostMapping(name = "作者列表", path = "/authorList")
    public ResponseData getAuthorList(RequestData requestData) {
        return ResponseData.success(authorService.selectListPage());
    }

    @PostMapping(name = "作者信息", path = "/authorInfo")
    public ResponseData getAuthorInfo(RequestData requestData) {
        String name = requestData.getParam().getString("name");
        return ResponseData.success(authorService.selectByName(name));
    }

    @PostMapping(name = "类型标签列表", path = "/tag/category")
    public ResponseData getTagCategoryList(RequestData requestData) {
        return ResponseData.success(tagService.selectTagByType(1));
    }

    @PostMapping(name = "节日标签列表", path = "/tag/festival")
    public ResponseData getTagFestivalList(RequestData requestData) {
        return ResponseData.success(tagService.selectTagByType(2));
    }

    @PostMapping(name = "诗集标签列表", path = "/tag/poetrySet")
    public ResponseData getTagPoetrySetList(RequestData requestData) {
        return ResponseData.success(tagService.selectTagByType(3));
    }

    @PostMapping(name = "朝代列表", path = "/dynasty")
    public ResponseData getDynastyList(RequestData requestData) {
        return ResponseData.success(dynastyService.selectList(new EntityWrapper<>()));
    }

    @PostMapping(name = "推荐列表", path = "/recommend")
    public ResponseData getRecommendList(RequestData requestData) {
        List<Poetry> list = recommendService.selectList();
        for (Poetry poetry : list) {
            poetry.setContent(poetry.getContent()
                    .replaceAll("<p>", "")
                    .replaceAll("</p>", "\\\n")
                    .replaceAll("<br>", "\\\n")
                    .replaceAll("<span style=\"font-family:SimSun;\">", "")
                    .replaceAll("</span>", "")
                    .replaceAll("<strong>", "")
                    .replaceAll("</strong>", ""));
        }
        return ResponseData.success(list);
    }

    @PostMapping(name = "诗词额外信息", path = "/detail")
    public ResponseData getPoetryExtraInfo(RequestData requestData) {
        Integer poetryId = requestData.getParam().getInteger("poetryId");
        String poetryAuthor = requestData.getParam().getString("poetryAuthor");
        String poetryTitle = requestData.getParam().getString("poetryTitle");
        Detail detail = detailService.selectOne(new EntityWrapper<Detail>().eq("poetry_id", poetryId));
        Integer id = null;
        if (detail != null) {
            id = detail.getId();
        }
        if (detail == null ||
                StringUtils.isEmpty(detail.getRemark()) ||
                StringUtils.isEmpty(detail.getTranslation()) ||
                StringUtils.isEmpty(detail.getAppreciate())) {
            detail = new PoetryDetailProcessor().parse(poetryTitle, poetryAuthor, 1);
            detail.setPoetryId(poetryId);
            if (id != null) {
                detail.setId(id);
            }
        }
        detailService.insertOrUpdate(detail);
        return ResponseData.success(detail);
    }
}
