package com.libra.cloud.file.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.libra.cloud.file.api.entity.Fileinfo;

import java.util.List;

/**
 * <p>
 * 文件信息表 Mapper 接口
 * </p>
 *
 * @author Libra
 * @date 2018/12/3
 * @description
 */
public interface FileinfoMapper extends BaseMapper<Fileinfo> {

    /**
     * 获取fileinfo列表
     */
    List<Fileinfo> getFileInfoList(Page page, Fileinfo fileinfo);

}
