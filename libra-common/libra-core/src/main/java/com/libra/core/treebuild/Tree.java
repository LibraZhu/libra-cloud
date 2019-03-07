package com.libra.core.treebuild;

import java.util.List;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 构造树节点的接口规范
 */
public interface Tree {
    /**
     * 获取节点id
     */
    String getNodeId();

    /**
     * 获取节点父id
     */
    String getNodeParentId();

    /**
     * 设置children
     */
    void setChildrenNodes(List childrenNodes);
}
