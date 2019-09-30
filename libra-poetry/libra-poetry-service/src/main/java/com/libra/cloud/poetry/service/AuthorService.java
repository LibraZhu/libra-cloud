package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Author;
import com.libra.cloud.poetry.mapper.AuthorMapper;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作者表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class AuthorService extends ServiceImpl<AuthorMapper, Author> {
    /**
     * 分页获取作者
     *
     * @return PageResult
     */
    public PageResult selectListPage() {
        Page<Author> page = PageFactory.defaultPage();
        page.setOpenSort(true);
        page.setOrderByField("like_num");
        page.setAsc(false);
        return new PageResult<>(selectPage(page));
    }

    /**
     * 通过名字获取作者信息
     *
     * @param name 名字
     * @return 作者信息
     */
    public Author selectByName(String name) {
        Author author = new Author();
        author.setName(name);
        return baseMapper.selectOne(author);
    }
}
