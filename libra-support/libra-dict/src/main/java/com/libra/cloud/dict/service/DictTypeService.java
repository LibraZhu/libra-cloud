package com.libra.cloud.dict.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.dict.api.entity.DictType;
import com.libra.cloud.dict.api.model.DictTypeInfo;
import com.libra.cloud.dict.enums.DictTypeEnum;
import com.libra.cloud.dict.mapper.DictTypeMapper;
import com.libra.core.enums.StatusEnum;
import com.libra.core.exception.RequestEmptyException;
import com.libra.core.exception.ServiceException;
import com.libra.core.page.PageFactory;
import com.libra.core.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.libra.cloud.dict.exception.DictExceptionEnum.*;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/4
 * @description
 */
@Service
public class DictTypeService extends ServiceImpl<DictTypeMapper, DictType> {

    /**
     * 获取字典类型列表
     *
     * @param page         分页参数
     * @param dictTypeInfo 查询条件封装
     */
    public List<DictTypeInfo> getDictTypeList(Page page, DictTypeInfo dictTypeInfo) {
        if (page == null) {
            page = PageFactory.defaultPage();
        }
        if (dictTypeInfo == null) {
            dictTypeInfo = new DictTypeInfo();
        }
        return this.baseMapper.getDictTypeList(page, dictTypeInfo);
    }

    /**
     * 添加字典类型
     */
    public void addDictType(DictType dictType) {
        if (EmptyUtil.isOneEmpty(dictType, dictType.getDictTypeCode(), dictType.getDictTypeName())) {
            throw new RequestEmptyException();
        }

        //判断有没有相同编码的字典
        Wrapper<DictType> wrapper = new EntityWrapper<DictType>()
                .eq("DICT_TYPE_CODE", dictType.getDictTypeCode());
        int dictTypes = this.selectCount(wrapper);
        if (dictTypes > 0) {
            throw new ServiceException(REPEAT_DICT_TYPE);
        }

        dictType.setStatus(StatusEnum.ENABLE.getCode());
        dictType.setDictTypeClass(DictTypeEnum.BUSINESS.getCode());

        this.insert(dictType);
    }

    /**
     * 修改字典类型
     */
    public void updateDictType(DictType dictType) {
        if (EmptyUtil.isOneEmpty(dictType, dictType.getDictTypeCode(), dictType.getDictTypeName())) {
            throw new RequestEmptyException();
        }

        DictType oldDictType = this.selectById(dictType.getDictTypeId());
        if (oldDictType == null) {
            throw new ServiceException(NOT_EXISTED);
        }

        //查询有没有编码重复的字典
        Wrapper<DictType> wrapper = new EntityWrapper<DictType>()
                .eq("DICT_TYPE_CODE", dictType.getDictTypeCode())
                .and()
                .ne("DICT_TYPE_ID", oldDictType.getDictTypeId());
        int dictTypes = this.selectCount(wrapper);
        if (dictTypes > 0) {
            throw new ServiceException(REPEAT_DICT_TYPE);
        }

        BeanUtil.copyProperties(dictType, oldDictType, CopyOptions.create().setIgnoreNullValue(true));
        this.updateById(oldDictType);
    }

    /**
     * 删除字典类型
     */
    public void deleteDictType(Long dictTypeId) {
        if (EmptyUtil.isEmpty(dictTypeId)) {
            throw new RequestEmptyException();
        }

        //判断字典是否存在
        DictType dictType = this.selectById(dictTypeId);
        if (dictType == null) {
            throw new ServiceException(NOT_EXISTED);
        }

        this.deleteById(dictTypeId);
    }

    /**
     * 修改字典状态
     */
    public void updateDictTypeStatus(Long dictTypeId, Integer status) {
        if (EmptyUtil.isOneEmpty(dictTypeId, status)) {
            throw new RequestEmptyException();
        }

        //判断字典是否存在
        DictType dictType = this.selectById(dictTypeId);
        if (dictType == null) {
            throw new ServiceException(NOT_EXISTED);
        }

        //判断状态是否正确
        StatusEnum statusEnum = StatusEnum.toEnum(status);
        if (statusEnum == null) {
            throw new ServiceException(WRONG_STATUS);
        }

        dictType.setStatus(status);

        this.updateById(dictType);
    }
}
