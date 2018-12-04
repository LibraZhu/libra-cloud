package com.libra.cloud.dict.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.DictTypeApi;
import com.libra.cloud.dict.api.entity.DictType;
import com.libra.cloud.dict.api.model.DictTypeInfo;
import com.libra.cloud.dict.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/4
 * @description
 */
@RestController
public class DictTypeServiceProvider implements DictTypeApi {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 获取字典类型列表
     *
     * @param dictTypeInfo 查询条件封装
     */
    public List<DictTypeInfo> getDictTypeList(@RequestBody DictTypeInfo dictTypeInfo, @RequestParam("pageNo") Integer pageNo,
                                              @RequestParam("pageSize") Integer pageSize) {
        return dictTypeService.getDictTypeList(new Page(pageNo, pageSize), dictTypeInfo);
    }

    /**
     * 添加字典类型
     */
    public void addDictType(@RequestBody DictType dictType) {
        dictTypeService.addDictType(dictType);
    }

    /**
     * 修改字典类型
     */
    public void updateDictType(@RequestBody DictType dictType) {
        dictTypeService.updateDictType(dictType);
    }

    /**
     * 删除字典类型
     */
    public void deleteDictType(@RequestParam("dictTypeId") Long dictTypeId) {
        dictTypeService.deleteDictType(dictTypeId);
    }

    /**
     * 修改字典状态
     */
    public void updateDictTypeStatus(@RequestParam("dictTypeId") Long dictTypeId, @RequestParam("status") Integer status) {
        dictTypeService.updateDictTypeStatus(dictTypeId, status);
    }
}
