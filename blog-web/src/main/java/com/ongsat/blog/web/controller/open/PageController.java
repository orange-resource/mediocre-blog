package com.ongsat.blog.web.controller.open;

import com.ongsat.blog.api.constant.RedisConstant;
import com.ongsat.blog.web.service.ArticleService;
import com.ongsat.blog.web.service.RssConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RssConfigService rssConfigService;

    private Map getCommon() {
        Object deserialize = redisTemplate.opsForValue().get(RedisConstant.COMMON_PAGE_DATA);
        if (null == deserialize) {
            return null;
        }
        return (Map)deserialize;
    }

    private ModelAndView createCommonModelView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addAllObjects(this.getCommon());
        return modelAndView;
    }

    @GetMapping(value = {"/", "index", "home", "main"})
    public ModelAndView home() {
        ModelAndView home = this.createCommonModelView("home");
        return home;
    }

    @GetMapping(value = {"/rss", "/feed"}, produces = {"application/xml;charset=UTF-8"})
    @ResponseBody
    public String feed() {
        return rssConfigService.getFeedToString();
    }

    @GetMapping(value = "/all")
    public ModelAndView queryArticleByPage(Integer page) {
        Map<String, Object> all = articleService.queryArticleByPageToMap(null == page ? 1 : page);

        ModelAndView modelAndView = this.createCommonModelView("list");

        modelAndView.addAllObjects(all);
        modelAndView.addObject("type", 0);
        return modelAndView;
    }

    @GetMapping(value = "/category/{categoryId}")
    public ModelAndView queryArticleByCategory(@PathVariable String categoryId, Integer page) {
        Map<String, Object> articleByCategory = articleService.queryArticleByCategoryToMap(categoryId, null == page ? 1 : page);

        ModelAndView modelAndView = this.createCommonModelView("list");
        modelAndView.addAllObjects(articleByCategory);
        modelAndView.addObject("type", 1);
        return modelAndView;
    }

    @GetMapping(value = "/search/{keyword}")
    public ModelAndView search(@PathVariable String keyword, Integer page) {
        Map<String, Object> articleBySearch = articleService.searchByOpenApiPlatformToMap(keyword, null == page ? 1 : page);

        ModelAndView modelAndView = this.createCommonModelView("list");
        modelAndView.addAllObjects(articleBySearch);
        modelAndView.addObject("type", 2);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }

    @GetMapping(value = "/article/{articleUri}")
    public ModelAndView queryArticle(@PathVariable String articleUri) {
        Map<String, Object> article = articleService.queryArticleToMap(articleUri);

        ModelAndView modelAndView = this.createCommonModelView("article");
        modelAndView.addObject("article", article);
        modelAndView.addObject("uri", articleUri);
        return modelAndView;
    }

}
