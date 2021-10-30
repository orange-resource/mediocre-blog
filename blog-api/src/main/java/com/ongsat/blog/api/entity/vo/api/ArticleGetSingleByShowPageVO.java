package com.ongsat.blog.api.entity.vo.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ArticleGetSingleByShowPageVO implements Serializable {

    private String uri;

    private String title;

    private Long pv;

    private String contentHtml;

    private String mainImgUrl;

    private Integer isShowChildContent;

    private String description;

}
