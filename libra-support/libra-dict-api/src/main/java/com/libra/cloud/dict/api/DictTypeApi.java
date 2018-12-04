package com.libra.cloud.dict.api;

import com.libra.cloud.dict.api.entity.DictType;
import com.libra.cloud.dict.api.model.DictTypeInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典类型远程调用接口
 */
@RequestMapping("/api/dictType")
public interface DictTypeApi {
    /**
     * 获取字典类型列表
     *
     * @param dictTypeInfo 查询条件封装
     */
    @RequestMapping(value = "/getDictTypeList", method = RequestMethod.POST)
    List<DictTypeInfo> getDictTypeList(@RequestBody DictTypeInfo dictTypeInfo, @RequestParam("pageNo") Integer pageNo,
                                       @RequestParam("pageSize") Integer pageSize);

    /**
     * 添加字典类型
     */
    @RequestMapping(value = "/addDictType", method = RequestMethod.POST)
    void addDictType(@RequestBody DictType dictType);

    /**
     * 修改字典类型
     */
    @RequestMapping(value = "/updateDictType", method = RequestMethod.POST)
    void updateDictType(@RequestBody DictType dictType);

    /**
     * 删除字典类型
     */
    @RequestMapping(value = "/deleteDictType", method = RequestMethod.POST)
    void deleteDictType(@RequestParam("dictTypeId") Long dictTypeId);

    /**
     * 修改字典状态
     */
    @RequestMapping(value = "/updateDictTypeStatus", method = RequestMethod.POST)
    void updateDictTypeStatus(@RequestParam("dictTypeId") Long dictTypeId, @RequestParam("status") Integer status);
}
