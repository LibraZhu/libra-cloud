package com.libra.cloud.file.factory;

import com.libra.cloud.file.api.entity.Fileinfo;
import com.libra.cloud.file.config.properties.OssProperteis;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.FileUtil;
import com.libra.core.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件信息组装工厂
 */
@Slf4j
public class FileFactory {
    public static Fileinfo getFileInfo(String appCode, String fileOriginName, Long fileSize, Long fileId, String fileStorageName) {
        if (EmptyUtil.isEmpty(appCode)) {
            appCode = "noneAppCode";
        }

        Fileinfo fileinfo = new Fileinfo();
        fileinfo.setFileId(fileId);
        fileinfo.setAppCode(appCode);
        fileinfo.setFileOriginName(fileOriginName);
        fileinfo.setFileSuffix(FileUtil.getFileSuffix(fileOriginName));
        fileinfo.setFileSize(fileSize);
        fileinfo.setFileStorageName(fileStorageName);

        try {
            OssProperteis ossProperteis = SpringContextHolder.getBean(OssProperteis.class);
            fileinfo.setFileUrl(ossProperteis.getInternetFileUrl() + fileStorageName);
        } catch (Exception e) {
            log.error("获取ossProperties失败！存储文件地址为；" + fileStorageName);
            fileinfo.setFileUrl(fileStorageName);
        }

        return fileinfo;
    }
}
