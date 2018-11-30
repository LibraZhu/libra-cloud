package com.libra.scanner.model;

import lombok.Data;

import java.util.List;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 资源树
 */
@Data
public class ResourceTreeNode {
    /**
     * 资源中文名称
     */
    private String name;

    /**
     * 资源的编码
     */
    private String code;

    /**
     * 资源子节点
     */
    private List<ResourceTreeNode> children;

    public ResourceTreeNode() {
    }

    public ResourceTreeNode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public ResourceTreeNode(String name, String code, List<ResourceTreeNode> children) {
        this.name = name;
        this.code = code;
        this.children = children;
    }
}
