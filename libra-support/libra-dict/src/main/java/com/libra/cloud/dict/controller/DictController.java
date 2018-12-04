package com.libra.cloud.dict.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.entity.Dict;
import com.libra.cloud.dict.api.model.DictInfo;
import com.libra.cloud.dict.api.model.TreeDictInfo;
import com.libra.cloud.dict.service.DictService;
import com.libra.core.page.PageFactory;
import com.libra.core.reqres.request.RequestData;
import com.libra.core.reqres.response.ResponseData;
import com.libra.scanner.annotation.ApiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典管理
 */
@RestController
@ApiResource(name = "字典管理", path = "/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 添加字典
     */
    @PostMapping(name = "添加字典", path = "/addDict")
    public ResponseData addDictType(RequestData requestData) {
        Dict dict = requestData.parse(Dict.class);
        this.dictService.addDict(dict);
        return ResponseData.success();
    }

    /**
     * 修改字典
     */
    @PostMapping(name = "修改字典", path = "/updateDict")
    public ResponseData updateDict(RequestData requestData) {
        Dict dict = requestData.parse(Dict.class);
        this.dictService.updateDict(dict);
        return ResponseData.success();
    }

    /**
     * 删除字典
     */
    @PostMapping(name = "删除字典", path = "/deleteDict")
    public ResponseData deleteDict(RequestData requestData) {
        Long dictId = requestData.getLong("dictId");
        this.dictService.deleteDict(dictId);
        return ResponseData.success();
    }

    /**
     * 更新字典状态
     */
    @PostMapping(name = "更新字典状态", path = "/updateDictStatus")
    public ResponseData updateDictStatus(RequestData requestData) {
        Long dictId = requestData.getLong("dictId");
        Integer status = requestData.getInteger("status");
        this.dictService.updateDictStatus(dictId, status);
        return ResponseData.success();
    }

    /**
     * 获取字典列表
     */
    @PostMapping(name = "获取字典列表", path = "/getDictList")
    public ResponseData getDictList(RequestData requestData) {
        Page<DictInfo> page = PageFactory.defaultPage();
        DictInfo dictInfo = requestData.parse(DictInfo.class);
        List<DictInfo> dictList = this.dictService.getDictList(page, dictInfo);
        page.setRecords(dictList);
        return ResponseData.success(page);
    }

    /**
     * 根据字典类型code获取所有字典
     */
    @PostMapping(name = "根据字典类型code获取所有字典", path = "/getDictListByTypeCode")
    public ResponseData getDictListByTypeCode(RequestData requestData) {
        String dictTypeCode = requestData.getString("dictTypeCode");
        List<Dict> dictList = this.dictService.getDictListByTypeCode(dictTypeCode);
        return ResponseData.success(dictList);
    }

    /**
     * 获取树形字典列表
     */
    @PostMapping(name = "获取树形字典列表", path = "/getDictTreeList")
    public ResponseData getDictTreeList(RequestData requestData) {
        String dictTypeCode = requestData.getString("dictTypeCode");
        List<TreeDictInfo> treeDictList = this.dictService.getTreeDictList(dictTypeCode);
        return ResponseData.success(treeDictList);
    }
}
