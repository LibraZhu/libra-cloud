package com.libra.cloud.file.storage;

import java.io.InputStream;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件操纵者
 */
public interface FileOperator {

    /**
     * 判断是否存在文件
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午1:14
     */
    boolean isExistingFile(String fileName);

    /**
     * 获取文件字节
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午1:15
     */
    byte[] getFileBytes(String fileName);

    /**
     * 存储文件
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午1:16
     */
    void storageFile(String fileName, InputStream inputStream);

    /**
     * 存储文件
     *
     * @author fengshuonan
     * @Date 2018/6/27 下午1:16
     */
    void storageFile(String fileName, byte[] bytes);

    /**
     * 描述:拷贝一份新的原文件信息
     *
     * @author 张建堂
     * @date 2018年6月28日 下午1:26:32
     */
    boolean copyFile(String sourceFileFinalName, String newFileFinalName);

    /**
     * 获取文件的下载地址（带鉴权的）
     *
     * @author fengshuonan
     * @Date 2018/7/7 上午11:27
     */
    String getFileAuthUrl(String fileFinalName);
}
