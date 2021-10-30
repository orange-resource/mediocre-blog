package com.ongsat.blog.web.controller.open;

import com.ongsat.blog.api.constant.RedisConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.api.ArticleGetByCategoryParamVO;
import com.ongsat.blog.api.entity.vo.api.ArticleGetNewsParamVO;
import com.ongsat.blog.api.entity.vo.api.ArticleGetSingleByShowPageParamVO;
import com.ongsat.blog.api.entity.vo.api.ArticleSearchParamVO;
import com.ongsat.blog.web.common.util.IpUtil;
import com.ongsat.blog.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Map getCommon() {
        Object deserialize = redisTemplate.opsForValue().get(RedisConstant.COMMON_PAGE_DATA);
        if (null == deserialize) {
            return null;
        }
        return (Map)deserialize;
    }

    private ModelAndView createPage(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
//        modelAndView.addObject("backgroundGray", backgroundGray);
        return modelAndView;
    }

    @GetMapping(value = {"/", "index", "home", "main"})
    public ModelAndView home() {
        ModelAndView home = this.createPage("home");
        home.addAllObjects(this.getCommon());
        return home;
    }

    @GetMapping(value = "/news")
    public ModelAndView news(Integer page) {
        ArticleGetNewsParamVO articleGetNewsParamVO = new ArticleGetNewsParamVO();
        articleGetNewsParamVO.setOffset(null == page ? 1 : page);
        Response news = articleService.getNews(articleGetNewsParamVO);

        ModelAndView modelAndView = this.createPage("list");

        modelAndView.addAllObjects(this.getCommon());
        modelAndView.addAllObjects((Map<String, Object>) news.getData());
        modelAndView.addObject("type", 0);
        return modelAndView;
    }

    @GetMapping(value = "/category/{categoryId}")
    public ModelAndView category(@PathVariable String categoryId, Integer page) {
        ArticleGetByCategoryParamVO articleGetByCategoryParamVO = new ArticleGetByCategoryParamVO();
        articleGetByCategoryParamVO.setOffset(null == page ? 1 : page);
        articleGetByCategoryParamVO.setCategoryUri(categoryId);
        Response category = articleService.getByCategory(articleGetByCategoryParamVO);

        ModelAndView modelAndView = this.createPage("list");
        modelAndView.addAllObjects(this.getCommon());
        modelAndView.addAllObjects((Map<String, Object>) category.getData());
        modelAndView.addObject("type", 1);
        return modelAndView;
    }

    @GetMapping(value = "/search/{keyword}")
    public ModelAndView search(@PathVariable String keyword, Integer page) {
        ArticleSearchParamVO articleSearchParamVO = new ArticleSearchParamVO();
        articleSearchParamVO.setOffset(null == page ? 1 : page);
        articleSearchParamVO.setSearchText(keyword);
        Response search = articleService.search(articleSearchParamVO);

        ModelAndView modelAndView = this.createPage("list");
        modelAndView.addAllObjects(this.getCommon());
        modelAndView.addAllObjects((Map<String, Object>) search.getData());
        modelAndView.addObject("type", 2);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }

    @GetMapping(value = "/article/{articleId}")
    public ModelAndView article(@PathVariable String articleId,
                                HttpServletRequest httpServletRequest) {

        String ip = IpUtil.getIpAddress(httpServletRequest);
        Cookie[] cookies = httpServletRequest.getCookies();
        String codeToken = "";
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("codeToken".equals(name)) {
                    codeToken = cookie.getValue();
                    continue;
                }
            }
        }
        ArticleGetSingleByShowPageParamVO articleGetSingleByShowPageParamVO = new ArticleGetSingleByShowPageParamVO();
        articleGetSingleByShowPageParamVO.setUri(articleId);
        Response response = articleService.getSingleByShowPage(articleGetSingleByShowPageParamVO, codeToken, ip);

        ModelAndView modelAndView = this.createPage("article");
        modelAndView.addAllObjects(this.getCommon());
        modelAndView.addObject("article", response.getData());
        modelAndView.addObject("uri", articleId);
        return modelAndView;
    }

}
