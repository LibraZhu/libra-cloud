package com.libra.cloud.file.service;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.libra.cloud.file.api.entity.Fileinfo;
import com.libra.cloud.file.exception.FileExceptionEnum;
import com.libra.cloud.file.factory.FileFactory;
import com.libra.cloud.file.mapper.FileinfoMapper;
import com.libra.cloud.file.storage.FileOperator;
import com.libra.core.exception.CoreExceptionEnum;
import com.libra.core.exception.RequestEmptyException;
import com.libra.core.exception.ServiceException;
import com.libra.core.page.PageFactory;
import com.libra.core.util.EmptyUtil;
import com.libra.core.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
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
@Service
public class FileinfoService extends ServiceImpl<FileinfoMapper, Fileinfo> {

    @Autowired
    private FileOperator fileOperator;

    /**
     * 获取文件详细信息
     */
    public Fileinfo getFileInfo(Long fileId) {
        Fileinfo fileinfo = this.selectById(fileId);
        if (fileinfo == null) {
            throw new ServiceException(CoreExceptionEnum.FILE_NOT_FOUND);
        } else {
            return fileinfo;
        }
    }

    /**
     * 存储文件
     */
    @Transactional(rollbackFor = Exception.class)
    public String uploadFileAndSaveFileInfo(InputStream inputStream, String fileName, Long size) {

        try {
            //保存文件
            long uuid = IdWorker.getId();
            String storageName = uuid + "." + FileUtil.getFileSuffix(fileName);
            fileOperator.storageFile(storageName, inputStream);

            //生成文件信息
            Fileinfo fileInfo = FileFactory.getFileInfo("", fileName, size, uuid, storageName);

            //存储文件信息到数据库
            this.insert(fileInfo);

            //返回文件的持久化id
            return Long.toString(uuid);
        } finally {
            IoUtil.close(inputStream);
        }
    }

    /**
     * 获取文件的url通过文件id
     */
    public String getFileUrlById(Long fileId) {
        if (EmptyUtil.isEmpty(fileId)) {
            throw new RequestEmptyException("文件id为空！");
        }
        Fileinfo fileinfo = this.selectById(fileId);
        if (fileinfo == null) {
            throw new ServiceException(FileExceptionEnum.FILE_NOT_FOUND);
        }

        return fileOperator.getFileAuthUrl(fileinfo.getFileStorageName());
    }

    /**
     * 获取文件信息列表
     */
    public List<Fileinfo> getFileInfoList(Page page, Fileinfo fileinfo) {
        if (page == null) {
            page = PageFactory.defaultPage();
        }
        if (fileinfo == null) {
            fileinfo = new Fileinfo();
        }
        return this.baseMapper.getFileInfoList(page, fileinfo);
    }

}
