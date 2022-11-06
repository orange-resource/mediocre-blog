package com.ongsat.blog.api.entity.vo.admin.systemconfig;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SystemConfigSaveParamVO implements Serializable {

    private String title;

    private String footer;

    private String seoKeywords;

    private String seoDescription;

    private Integer pageGrayMode;

    private String logoUrl;

    private String bigLogoUrl;

    private String commonHeadHtml;

    private String commonFooterHtml;

}
