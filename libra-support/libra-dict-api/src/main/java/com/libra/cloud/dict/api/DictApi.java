package com.libra.cloud.dict.api;

import com.libra.cloud.dict.api.entity.Dict;
import com.libra.cloud.dict.api.model.DictInfo;
import com.libra.cloud.dict.api.model.TreeDictInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典远程调用接口
 */
@RequestMapping("/api/dict")
public interface DictApi {
    /**
     * 新增字典
     */
    @RequestMapping(value = "/addDict", method = RequestMethod.POST)
    void addDict(@RequestBody Dict dict);

    /**
     * 修改字典
     */
    @RequestMapping(value = "/updateDict", method = RequestMethod.POST)
    void updateDict(@RequestBody Dict dict);

    /**
     * 删除字典
     */
    @RequestMapping(value = "/deleteDict", method = RequestMethod.POST)
    void deleteDict(@RequestParam("dictId") Long dictId);

    /**
     * 更新字典状态
     */
    @RequestMapping(value = "/updatDictStatus", method = RequestMethod.POST)
    void updateDictStatus(@RequestParam("dictId") Long dictId, @RequestParam("status") Integer status);

    /**
     * 获取字典列表
     */
    @RequestMapping(value = "/getDictList", method = RequestMethod.POST)
    List<DictInfo> getDictList(@RequestBody DictInfo dictInfo, @RequestParam("pageNo") Integer pageNo,
                               @RequestParam("pageSize") Integer pageSize);

    /**
     * 获取树形字典列表
     */
    @RequestMapping(value = "/getTreeDictList", method = RequestMethod.POST)
    List<TreeDictInfo> getTreeDictList(@RequestParam("dictTypeCode") String dictTypeCode);
}
