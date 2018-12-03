package com.libra.cloud.file.provider;

import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.file.api.FileApi;
import com.libra.cloud.file.api.entity.Fileinfo;
import com.libra.cloud.file.api.model.FileByteInfo;
import com.libra.cloud.file.service.FileinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * <p>
 * 文件信息表 服务实现类
 * </p>
 *
 * @author Libra
 * @date 2018/12/3
 * @description
 */
@RestController
public class FileinfoServiceProvider implements FileApi {

    @Autowired
    private FileinfoService fileinfoService;


    @Override
    public Fileinfo getFileInfo(@RequestParam("fileId") Long fileId) {
        return this.fileinfoService.getFileInfo(fileId);
    }

    @Override
    public String uploadFileAndSaveFileInfo(@RequestBody FileByteInfo byteInfo,
                                            @RequestParam("fileName") String fileName,
                                            @RequestParam("size") Long size) {
        if (byteInfo == null || byteInfo.getFileBytes() == null) {
            return null;
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteInfo.getFileBytes());
            return this.fileinfoService.uploadFileAndSaveFileInfo(byteArrayInputStream, fileName, size);
        }
    }

    @Override
    public String getFileUrlById(@RequestParam("fileId") Long fileId) {
        return this.fileinfoService.getFileUrlById(fileId);
    }

    @Override
    public List<Fileinfo> getFileInfoList(@RequestBody Fileinfo fileinfo, @RequestParam("pageNo") Integer pageNo,
                                          @RequestParam("pageSize") Integer pageSize) {
        return this.fileinfoService.getFileInfoList(new Page(pageNo, pageSize), fileinfo);
    }

}
