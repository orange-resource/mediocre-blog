package com.ongsat.blog.api.entity.vo.admin.rssconfig;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RssConfigSaveParamVO implements Serializable {
    private static final long serialVersionUID = -5803188463222666679L;

    private String title;

    private String link;

    private String description;

    private String language;

    private String author;

    private String copyright;

    private String webMaster;

    private String managingEditor;

}
