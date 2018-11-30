package com.libra.scanner.config.properties;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/11/30
 * @description 扫描的常量
 */
@Data
public class ScannerProperties {
    /**
     * 资源扫描开关
     */
    private Boolean open;

    /**
     * 被扫描应用的名称
     */
    private String appName;

    /**
     * 应用的编码
     */
    private String appCode;

    /**
     * 链接符号
     */
    private String linkSymbol = "$";
}
