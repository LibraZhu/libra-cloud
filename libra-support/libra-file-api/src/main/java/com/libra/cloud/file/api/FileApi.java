package com.libra.cloud.file.api;

import com.libra.cloud.file.api.entity.Fileinfo;
import com.libra.cloud.file.api.exception.FileApiServiceException;
import com.libra.cloud.file.api.model.FileByteInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Libra
 * @date 2018/12/3
 * @description 文件相关远程调用接口
 */
@RequestMapping("/api/file")
public interface FileApi {
    /**
     * 获取文件详细信息
     *
     */
    @RequestMapping(value = "/getFileInfo", method = RequestMethod.POST)
    Fileinfo getFileInfo(@RequestParam("fileId") Long fileId) throws FileApiServiceException;

    /**
     * 存储文件
     *
     */
    @RequestMapping(value = "/uploadFileAndSaveFileInfo", method = RequestMethod.POST)
    String uploadFileAndSaveFileInfo(@RequestBody FileByteInfo byteInfo,
                                     @RequestParam("fileName") String fileName,
                                     @RequestParam("size") Long size) throws FileApiServiceException;

    /**
     * 获取文件的url通过文件id
     *
     */
    @RequestMapping(value = "/getFileUrlById", method = RequestMethod.POST)
    String getFileUrlById(@RequestParam("fileId") Long fileId) throws FileApiServiceException;

    /**
     * 获取文件信息列表
     *
     */
    @RequestMapping(value = "/getFileInfoList", method = RequestMethod.POST)
    List<Fileinfo> getFileInfoList(@RequestBody Fileinfo fileinfo,
                                   @RequestParam("pageNo") Integer pageNo,
                                   @RequestParam("pageSize") Integer pageSize) throws FileApiServiceException;
}
