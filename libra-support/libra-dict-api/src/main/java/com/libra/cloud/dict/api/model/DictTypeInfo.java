package com.libra.cloud.dict.api.model;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典类型详情
 */
@Data
public class DictTypeInfo {
    /**
     * 字典类型id
     */
    private Long dictTypeId;

    /**
     * 类型1：业务类型2：系统类型
     */
    private Integer dictTypeClass;

    /**
     * 字典类型编码
     */
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    private String dictTypeName;

    /**
     * 字典描述
     */
    private String dictTypeDesc;

    /**
     * 状态1：启用2：禁用
     */
    private Integer status;

    /**
     * 应用编码
     */
    private String appCode;
}
