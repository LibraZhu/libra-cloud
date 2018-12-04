package com.libra.cloud.dict.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.dict.api.entity.DictType;
import com.libra.cloud.dict.api.model.DictTypeInfo;

import java.util.List;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author Libra
 * @date 2018/12/4
 * @description
 */
public interface DictTypeMapper extends BaseMapper<DictType> {

    /**
     * 获取字典类型列表
     */
    List<DictTypeInfo> getDictTypeList(Page page, DictTypeInfo dictTypeInfo);

}
