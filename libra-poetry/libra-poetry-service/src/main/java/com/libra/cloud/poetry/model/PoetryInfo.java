package com.libra.cloud.poetry.model;

import com.libra.cloud.poetry.entity.Author;
import com.libra.cloud.poetry.entity.Poetry;
import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/27
 * @description
 */
@Data
public class PoetryInfo {
    private Poetry poetry;
    private Author author;
}
