package com.libra.cloud.dict.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.DictApi;
import com.libra.cloud.dict.api.entity.Dict;
import com.libra.cloud.dict.api.model.DictInfo;
import com.libra.cloud.dict.api.model.TreeDictInfo;
import com.libra.cloud.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 基础字典 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/4
 * @description
 */
@RestController
public class DictServiceProvider implements DictApi {

    @Autowired
    private DictService dictService;

    /**
     * 新增字典
     */
    @Override
    public void addDict(@RequestBody Dict dict) {
        this.dictService.addDict(dict);
    }

    /**
     * 修改字典
     */
    @Override
    public void updateDict(@RequestBody Dict dict) {
        this.dictService.updateDict(dict);
    }

    /**
     * 删除字典
     */
    @Override
    public void deleteDict(@RequestParam("dictId") Long dictId) {
        this.dictService.deleteDict(dictId);
    }

    /**
     * 更新字典状态
     */
    @Override
    public void updateDictStatus(@RequestParam("dictId") Long dictId, @RequestParam("dictId") Integer status) {
        this.dictService.updateDictStatus(dictId, status);
    }

    /**
     * 获取字典列表
     */
    @Override
    public List<DictInfo> getDictList(@RequestBody DictInfo dictInfo, @RequestParam("pageNo") Integer pageNo,
                                      @RequestParam("pageSize") Integer pageSize) {
        return this.dictService.getDictList(new Page(pageNo, pageSize), dictInfo);
    }

    /**
     * 获取树形字典列表
     */
    @Override
    public List<TreeDictInfo> getTreeDictList(@RequestParam("dictTypeCode") String dictTypeCode) {
        return this.dictService.getTreeDictList(dictTypeCode);
    }
}
