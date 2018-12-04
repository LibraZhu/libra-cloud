package com.libra.cloud.dict.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.entity.Dict;
import com.libra.cloud.dict.api.model.DictInfo;

import java.util.List;

/**
 * <p>
 * 基础字典 Mapper 接口
 * </p>
 *
 * @author Libra
 * @date 2018/12/4
 * @description
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 获取字典列表
     */
    List<DictInfo> getDictList(Page page, DictInfo dictInfo);

}
