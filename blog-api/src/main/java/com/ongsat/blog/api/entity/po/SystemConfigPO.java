package com.ongsat.blog.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 系统配置
 * t_system_config
 */
@TableName("t_system_config")
@KeySequence("SEQ_TEST")
public class SystemConfigPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "scheme_name", type = IdType.NONE)
    private String schemeName;

    /**
     * 网站标题
     * title
     */
    private String title;

    /**
     * 网站底部信息文字
     * footer
     */
    private String footer;

    /**
     * seo关键词
     * seo_keywords
     */
    private String seoKeywords;

    /**
     * seo描述
     * seo_description
     */
    private String seoDescription;

    /**
     * 0=正常颜色 1=页面灰色
     * page_gray_mode
     */
    private Integer pageGrayMode;

    /**
     * big_logo_url
     */
    private String bigLogoUrl;

    /**
     * 网站logo
     * logo_url
     */
    private String logoUrl;

    /**
     * common_head_html
     */
    private String commonHeadHtml;

    /**
     * common_footer_html
     */
    private String commonFooterHtml;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getSeoKeywords() {
        return seoKeywords;
    }

    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public Integer getPageGrayMode() {
        return pageGrayMode;
    }

    public void setPageGrayMode(Integer pageGrayMode) {
        this.pageGrayMode = pageGrayMode;
    }

    public String getBigLogoUrl() {
        return bigLogoUrl;
    }

    public void setBigLogoUrl(String bigLogoUrl) {
        this.bigLogoUrl = bigLogoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getCommonHeadHtml() {
        return commonHeadHtml;
    }

    public void setCommonHeadHtml(String commonHeadHtml) {
        this.commonHeadHtml = commonHeadHtml;
    }

    public String getCommonFooterHtml() {
        return commonFooterHtml;
    }

    public void setCommonFooterHtml(String commonFooterHtml) {
        this.commonFooterHtml = commonFooterHtml;
    }
}