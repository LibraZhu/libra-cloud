package com.libra.cloud.poetry.webmagic.bean;

import lombok.Data;

/**
 * @author Libra
 * @date 2018/12/21
 * @description
 */
@Data
public class Author {
    private String name;
    private String image;
    private String desc;
    private String dynasty;
    private Integer poetryNum;
    private Integer likeNum;
    private String poetryUrl;
}
