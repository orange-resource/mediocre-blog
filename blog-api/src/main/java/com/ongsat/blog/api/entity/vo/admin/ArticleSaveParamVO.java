package com.ongsat.blog.api.entity.vo.admin;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ArticleSaveParamVO implements Serializable {

    private String id;

    private String title;

    private String description;

    private Integer isShow;

    private String uri;

    private Integer isRecommend;

    private Long pv;

    private String categoryId;

    private String secondaryCategoryId;

    private String contentHtml;

    private String contentMarkdown;

    private String mainImgUrl;

    private String childContentHtml;

}
