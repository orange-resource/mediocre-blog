package com.ongsat.blog.api.entity.vo.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ArticleCardListVO implements Serializable {

    private String title;

    private String uri;

    private Long pv;

    private String mainImgUrl;

    private String description;

}
