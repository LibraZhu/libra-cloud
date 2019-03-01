package com.libra.cloud.poetry.webmagic.bean;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/24
 * @description
 */
@Data
public class Poetry {
    private String title;
    private String author;
    private String desc;
    private String dynasty;
    private String tag;
    private Integer likeNum;
}
