package com.libra.cloud.dict.api.model;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典详细信息
 */
@Data
public class DictInfo {
    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典类型编码
     */
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    private String typeName;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 简称
     */
    private String dictShortName;

    /**
     * 字典简拼
     */
    private String dictShortCode;

    /**
     * 上级代码id
     */
    private String parentId;

    /**
     * 状态(1:启用,2:禁用)
     */
    private Integer status;

    /**
     * 应用编码
     */
    private String appCode;
}
