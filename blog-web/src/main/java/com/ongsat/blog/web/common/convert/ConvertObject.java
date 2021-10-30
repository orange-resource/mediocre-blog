package com.ongsat.blog.web.common.convert;

import com.ongsat.blog.api.entity.dataobject.ArticleNewsDO;
import com.ongsat.blog.api.entity.dataobject.ArticleRecommendDO;
import com.ongsat.blog.api.entity.dataobject.BlogrollDO;
import com.ongsat.blog.api.entity.dataobject.CategoryTreeDO;
import com.ongsat.blog.api.entity.po.*;
import com.ongsat.blog.api.entity.vo.admin.*;
import com.ongsat.blog.api.entity.vo.admin.carousel.CarouselSaveParamVO;
import com.ongsat.blog.api.entity.vo.api.ArticleCardListVO;
import com.ongsat.blog.api.entity.vo.api.ArticleGetSingleByShowPageVO;
import com.ongsat.blog.api.entity.vo.api.ArticleWechatVO;
import com.ongsat.blog.api.entity.vo.api.CategoryTreeVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConvertObject {

    SystemConfigPO toSystemConfigPO(SystemConfigSaveParamVO systemConfigSaveParamVO);

    BlogrollPO toBlogrollPO(BlogrollCreateParamVO blogrollCreateParamVO);

    BlogrollPO toBlogrollPO(BlogrollUpdateParamVO blogrollUpdateParamVO);

    SectionPO toSectionPO(SectionCreateParamVO sectionCreateParamVO);

    SectionPO toSectionPO(SectionUpdateParamVO sectionUpdateParamVO);

    ArticlePO toArticlePO(ArticleSaveParamVO articleSaveParamVO);

    BannerPO toCarouselPO(CarouselSaveParamVO carouselSaveParamVO);

    BlogrollDO toBlogrollDO(BlogrollPO blogrollPO);

    List<BlogrollDO> toBlogrollDO(List<BlogrollPO> blogrollPO);

    ArticleNewsDO toArticleNewsDO(ArticlePO articlePO);

    List<ArticleNewsDO> toArticleNewsDO(List<ArticlePO> articlePO);

    ArticleRecommendDO toArticleRecommendDO(ArticlePO articlePO);

    List<ArticleRecommendDO> toArticleRecommendDO(List<ArticlePO> articlePO);

    ArticleGetSingleByShowPageVO toArticleGetSingleByShowPageVO(ArticlePO articlePO);

    ArticleCardListVO toArticleCardListVO(ArticlePO articlePO);

    List<ArticleCardListVO> toArticleCardListVO(List<ArticlePO> articlePO);

    ArticleWechatVO toArticleWechatVO(ArticlePO articlePO);

    List<ArticleWechatVO> toArticleWechatVO(List<ArticlePO> articlePO);

    CategoryTreeDO toCategoryTreeDO(CategoryTreeDO categoryTreeDO);

    CategoryPO toCategoryPO(CategoryTreeDO categoryTreeDO);

    CategoryPO toCategoryPO(CategoryTreeVO categoryTreeVO);

}
