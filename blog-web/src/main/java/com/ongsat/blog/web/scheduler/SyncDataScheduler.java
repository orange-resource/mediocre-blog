package com.ongsat.blog.web.scheduler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.ongsat.blog.api.constant.RedisConstant;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.entity.po.*;
import com.ongsat.blog.api.entity.vo.api.category.CategoryTreeVO;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.*;
import com.ongsat.blog.web.service.SystemConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 主要同步页面上的全部数据到redis
 */
@Slf4j
@Component
@EnableScheduling
public class SyncDataScheduler {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ConvertObject convertObject;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private BlogrollMapper blogrollMapper;

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Scheduled(fixedRate = 3000)
    public void execute() {
        try {
            SystemConfigPO systemConfigPO = systemConfigService.getItem();

            List<BlogrollPO> blogrollPOList = blogrollMapper.selectListBySort();

            List<SectionPO> sectionPOList = sectionMapper.selectListBySort();

            List<BannerPO> bannerPOList = bannerMapper.selectListBySort();

            List<ArticlePO> latestArticleList = articleMapper.selectListByLatestAndLimit();

            List<ArticlePO> recommendArticleList = articleMapper.selectListByRecommend();

            List<CategoryTreeVO> categoryTreeList = categoryMapper.selectCategoryTree();
            intensify(categoryTreeList);

            Map<String, Object> build = new ResultBuilder()
                    .set("config", systemConfigPO)
                    .set("blogroll", blogrollPOList)
                    .set("section", sectionPOList)
                    .set("banner", bannerPOList)
                    .set("latestArticle", latestArticleList)
                    .set("recommendArticle", recommendArticleList)
                    .set("category", categoryTreeList)
                    .build();
            redisTemplate.opsForValue().set(RedisConstant.COMMON_PAGE_DATA, build);
        } catch (Throwable e) {
            log.error("同步页面数据错误: {}", ExceptionUtil.stacktraceToString(e));
        }
    }

    private void intensify(List<CategoryTreeVO> categoryTreeVOList) {
        for (CategoryTreeVO categoryTreeVO : categoryTreeVOList) {
            List<CategoryPO> children = categoryTreeVO.getChildren();
            if (children.size() > 0) {
                CategoryPO categoryPO = convertObject.toCategoryPO(categoryTreeVO);
                categoryPO.setName("查看全部");
                children.add(0, categoryPO);
            }
        }
    }

}
