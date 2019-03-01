package com.libra.cloud.poetry.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.libra.cloud.poetry.service.*;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(name = "诗文列表", path = "/list")
    public ResponseData getPoetryList(RequestData requestData) {
        return ResponseData.success(poetryService.selectListPage());
    }

    @PostMapping(name = "诗文信息", path = "/info")
    public ResponseData getPoetryInfo(RequestData requestData) {
        Integer id = requestData.getInteger("poetryId");
        return ResponseData.success(poetryService.selectPoetryInfoById(id));
    }

    @PostMapping(name = "作者列表", path = "/authorList")
    public ResponseData getAuthorList(RequestData requestData) {
        return ResponseData.success(authorService.selectListPage());
    }

    @PostMapping(name = "作者信息", path = "/authorInfo")
    public ResponseData getAuthorInfo(RequestData requestData) {
        String name = requestData.getString("name");
        return ResponseData.success(authorService.selectByName(name));
    }

    @PostMapping(name = "标签类型列表", path = "/tag")
    public ResponseData getTagList(RequestData requestData) {
        return ResponseData.success(tagService.selectList(new EntityWrapper<>()));
    }

    @PostMapping(name = "朝代列表", path = "/dynasty")
    public ResponseData getDynastyList(RequestData requestData) {
        return ResponseData.success(dynastyService.selectList(new EntityWrapper<>()));
    }

    @PostMapping(name = "推荐列表", path = "/recommend")
    public ResponseData getRecommendList(RequestData requestData) {
        return ResponseData.success(recommendService.selectList());
    }
}
