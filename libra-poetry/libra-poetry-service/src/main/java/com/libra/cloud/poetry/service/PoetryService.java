package com.libra.cloud.poetry.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.libra.cloud.poetry.entity.Poetry;
import com.libra.cloud.poetry.exception.PoetryExceptionEnum;
import com.libra.cloud.poetry.mapper.PoetryMapper;
import com.libra.cloud.poetry.model.PoetryInfo;
import com.libra.core.exception.ServiceException;
import com.libra.core.page.PageFactory;
import com.libra.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 诗文表 服务实现类
 * </p>
 *
 * @author libra
 * @since 2018-12-27
 */
@Service
public class PoetryService extends ServiceImpl<PoetryMapper, Poetry> {
    @Autowired
    AuthorService authorService;

    /**
     * 分页获取诗
     *
     * @return PageResult
     */
    public PageResult selectListPage() {
        return new PageResult<>(selectPage(PageFactory.defaultPage()));
    }

    public PageResult selectListPageByAuthor(String author) {
        if (StringUtils.isEmpty(author)) {
            return null;
        }
        PageResult<Poetry> result = new PageResult<>(selectPage(PageFactory.defaultPage(), new EntityWrapper<Poetry>().eq("author", author)));
        List<Poetry> list = result.getRows();
        for (Poetry poetry : list) {
            poetry.setContent(poetry.getContent()
                    .replaceAll("<p>", "")
                    .replaceAll("</p>", "\\\n")
                    .replaceAll("<br>", "\\\n")
                    .replaceAll("<span style=\"font-family:SimSun;\">", "")
                    .replaceAll("</span>", "")
                    .replaceAll("<strong>", "")
                    .replaceAll("</strong>", ""));
        }
        return result;
    }

    public PageResult selectListPageByDynasty(String dynasty) {
        if (StringUtils.isEmpty(dynasty)) {
            return null;
        }
        PageResult<Poetry> result = new PageResult<>(selectPage(PageFactory.defaultPage(), new EntityWrapper<Poetry>().eq("dynasty", dynasty)));
        List<Poetry> list = result.getRows();
        for (Poetry poetry : list) {
            poetry.setContent(poetry.getContent()
                    .replaceAll("<p>", "")
                    .replaceAll("</p>", "\\\n")
                    .replaceAll("<br>", "\\\n")
                    .replaceAll("<span style=\"font-family:SimSun;\">", "")
                    .replaceAll("</span>", "")
                    .replaceAll("<strong>", "")
                    .replaceAll("</strong>", ""));
        }
        return result;
    }

    public PageResult selectListPageByTag(String tag) {
        if (StringUtils.isEmpty(tag)) {
            return null;
        }
        PageResult<Poetry> result = new PageResult<>(selectPage(PageFactory.defaultPage(), new EntityWrapper<Poetry>().like("tag", tag)));
        List<Poetry> list = result.getRows();
        for (Poetry poetry : list) {
            poetry.setContent(poetry.getContent()
                    .replaceAll("<p>", "")
                    .replaceAll("</p>", "\\\n")
                    .replaceAll("<br>", "\\\n")
                    .replaceAll("<span style=\"font-family:SimSun;\">", "")
                    .replaceAll("</span>", "")
                    .replaceAll("<strong>", "")
                    .replaceAll("</strong>", ""));
        }
        return result;
    }

    /**
     * 通过id获取诗文信息
     *
     * @param id 诗文id
     * @return 诗文信息, 包括作者
     */
    public PoetryInfo selectPoetryInfoById(Integer id) {
        PoetryInfo poetryInfo = new PoetryInfo();
        Poetry poetry = baseMapper.selectById(id);
        if (poetry == null) {
            throw new ServiceException(PoetryExceptionEnum.TEMPTY_POETRY);
        }
        poetry.setContent(poetry.getContent()
                .replaceAll("<p>", "")
                .replaceAll("</p>", "\\\n")
                .replaceAll("<br>", "\\\n")
                .replaceAll("<span style=\"font-family:SimSun;\">", "")
                .replaceAll("</span>", "")
                .replaceAll("<strong>", "")
                .replaceAll("</strong>", ""));
        poetryInfo.setPoetry(poetry);
        poetryInfo.setAuthor(authorService.selectByName(poetryInfo.getPoetry().getAuthor()));
        return null;
    }

    public PageResult search(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        PageResult<Poetry> result = new PageResult<>(selectPage(PageFactory.defaultPage(), new EntityWrapper<Poetry>()
                .like("author", keyword).or()
                .like("title", keyword).or()
                .like("content", keyword)));
        List<Poetry> list = result.getRows();
        for (Poetry poetry : list) {
            poetry.setContent(poetry.getContent()
                    .replaceAll("<p>", "")
                    .replaceAll("</p>", "\\\n")
                    .replaceAll("<br>", "\\\n")
                    .replaceAll("<span style=\"font-family:SimSun;\">", "")
                    .replaceAll("</span>", "")
                    .replaceAll("<strong>", "")
                    .replaceAll("</strong>", ""));
        }
        return result;
    }
}
