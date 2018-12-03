package com.libra.cloud.file.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件下载内容
 */
@Getter
@Setter
public class FileByteInfo {
    /**
     * 文件字节
     */
    private byte[] fileBytes;
}
