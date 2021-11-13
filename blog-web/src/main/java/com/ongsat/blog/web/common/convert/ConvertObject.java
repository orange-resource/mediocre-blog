package com.ongsat.blog.web.common.convert;

import com.ongsat.blog.api.entity.po.*;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleUpdateParamVO;
import com.ongsat.blog.api.entity.vo.admin.banner.BannerSaveParamVO;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollUpdateParamVO;
import com.ongsat.blog.api.entity.vo.admin.oss.OssConfigSaveParamVO;
import com.ongsat.blog.api.entity.vo.admin.rssconfig.RssConfigSaveParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionUpdateParamVO;
import com.ongsat.blog.api.entity.vo.admin.systemconfig.SystemConfigSaveParamVO;
import com.ongsat.blog.api.entity.vo.api.category.CategoryTreeVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConvertObject {

    SystemConfigPO toSystemConfigPO(SystemConfigSaveParamVO systemConfigSaveParamVO);

    AliyunOssPO toAliyunOssPO(OssConfigSaveParamVO ossConfigSaveParamVO);

    BlogrollPO toBlogrollPO(BlogrollCreateParamVO blogrollCreateParamVO);

    BlogrollPO toBlogrollPO(BlogrollUpdateParamVO blogrollUpdateParamVO);

    SectionPO toSectionPO(SectionCreateParamVO sectionCreateParamVO);

    SectionPO toSectionPO(SectionUpdateParamVO sectionUpdateParamVO);

    ArticlePO toArticlePO(ArticleCreateParamVO articleCreateParamVO);

    ArticlePO toArticlePO(ArticleUpdateParamVO articleUpdateParamVO);

    BannerPO toBannerPO(BannerSaveParamVO bannerSaveParamVO);

    CategoryPO toCategoryPO(CategoryTreeVO categoryTreeVO);

    RssConfigPO toRssConfigPO(RssConfigSaveParamVO rssConfigSaveParamVO);

}
