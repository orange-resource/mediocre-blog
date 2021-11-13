package com.ongsat.blog.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * RSS配置
 * t_rss_config
 */
@TableName("t_rss_config")
@KeySequence("SEQ_TEST")
public class RssConfigPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "scheme_name", type = IdType.NONE)
    private String schemeName;

    /**
     * 频道名称
     * title
     */
    private String title;

    /**
     * web域名 比如http://www.ongsat.com
     * link
     */
    private String link;

    /**
     * 频道的描述
     * description
     */
    private String description;

    /**
     * 频道内容使用的语言 默认zh-cn
     * language
     */
    private String language;

    /**
     * 作者的Email地址
     * author
     */
    private String author;

    /**
     * copyright
     */
    private String copyright;

    /**
     * feed管理员的电子邮件地址
     * web_master
     */
    private String webMaster;

    /**
     * feed内容编辑的电子邮件地址
     * managing_editor
     */
    private String managingEditor;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }
}