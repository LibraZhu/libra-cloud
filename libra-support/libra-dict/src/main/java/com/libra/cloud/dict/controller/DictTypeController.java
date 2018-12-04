package com.libra.cloud.dict.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.entity.DictType;
import com.libra.cloud.dict.api.model.DictTypeInfo;
import com.libra.cloud.dict.service.DictTypeService;
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
 * @description 字典类型管理
 */
@RestController
@ApiResource(name = "字典类型管理", path = "/dictType")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 获取字典类型列表
     */
    @PostMapping(name = "获取字典类型列表", path = "/getDictTypeList")
    public ResponseData getDictTypeList(RequestData requestData) {
        DictTypeInfo dictTypeInfo = requestData.parse(DictTypeInfo.class);
        Page<DictTypeInfo> page = PageFactory.defaultPage();
        List<DictTypeInfo> dictTypeList = dictTypeService.getDictTypeList(page, dictTypeInfo);
        page.setRecords(dictTypeList);
        return ResponseData.success(page);
    }

    /**
     * 添加字典类型
     */
    @PostMapping(name = "添加字典类型", path = "/addDictType")
    public ResponseData addDictType(RequestData requestData) {
        DictType dictType = requestData.parse(DictType.class);
        this.dictTypeService.addDictType(dictType);
        return ResponseData.success();
    }

    /**
     * 修改字典类型
     */
    @PostMapping(name = "修改字典类型", path = "/updateDictType")
    public ResponseData updateDictType(RequestData requestData) {
        DictType dictType = requestData.parse(DictType.class);
        this.dictTypeService.updateDictType(dictType);
        return ResponseData.success();
    }

    /**
     * 删除字典类型
     */
    @PostMapping(name = "删除字典类型", path = "/deleteDictType")
    public ResponseData deleteDictType(RequestData requestData) {
        Long dictTypeId = requestData.getLong("dictTypeId");
        this.dictTypeService.deleteDictType(dictTypeId);
        return ResponseData.success();
    }

    /**
     * 修改字典类型状态
     */
    @PostMapping(name = "修改字典类型状态", path = "/updateStatus")
    public ResponseData updateStatus(RequestData requestData) {
        Long dictTypeId = requestData.getLong("dictTypeId");
        this.dictTypeService.deleteDictType(dictTypeId);
        return ResponseData.success();
    }
}
