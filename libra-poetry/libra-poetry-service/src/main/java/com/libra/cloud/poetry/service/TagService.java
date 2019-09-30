package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Tag;
import com.libra.cloud.poetry.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class TagService extends ServiceImpl<TagMapper, Tag> {
    public List<Tag> selectTagByType(Integer type) {
        Set<String> set = new HashSet<>();
        set.add("`order`");
        return selectList(new EntityWrapper<Tag>().eq("type", type).orderDesc(set));
    }
}
