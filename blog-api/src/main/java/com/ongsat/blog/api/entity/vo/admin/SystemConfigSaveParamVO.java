package com.ongsat.blog.api.entity.vo.admin;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SystemConfigSaveParamVO implements Serializable {

    private String id;

    /**
     * 网站底部
     * footer
     */
    private String footer;

    /**
     * 网站标题
     * title
     */
    private String title;

    /**
     * seo关键词
     * keywords
     */
    private String keywords;

    /**
     * seo描述
     * description
     */
    private String description;

    /**
     * 网站logo
     * logo_url
     */
    private String logoUrl;

    private Integer pageGray;

}
