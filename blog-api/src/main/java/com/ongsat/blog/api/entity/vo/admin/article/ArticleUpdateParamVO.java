package com.ongsat.blog.api.entity.vo.admin.article;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ArticleUpdateParamVO implements Serializable {

    private String id;

    private Integer isShow;

    private Integer isRecommend;

    private String title;

    private String description;

    private String masterImageUrl;

    private String uri;

    private String categoryId;

    private String categoryChildId;

    private String contentHtml;

}
