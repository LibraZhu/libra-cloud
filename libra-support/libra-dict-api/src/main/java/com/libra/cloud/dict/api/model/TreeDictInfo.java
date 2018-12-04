package com.libra.cloud.dict.api.model;

import com.libra.core.treebuild.Tree;
import lombok.Data;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/4
 * @description 字典详细信息
 */
@Data
public class TreeDictInfo implements Tree {
    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 上级代码id
     */
    private Long parentId;

    /**
     * tree子节点
     */
    private List<TreeDictInfo> children;

    @Override
    public String getNodeId() {
        if (this.dictId == null) {
            return null;
        } else {
            return this.dictId.toString();
        }
    }

    @Override
    public String getNodeParentId() {
        if (this.parentId == null) {
            return null;
        } else {
            return this.parentId.toString();
        }
    }

    @Override
    public void setChildrenNodes(List linkedList) {
        this.children = linkedList;
    }
}
