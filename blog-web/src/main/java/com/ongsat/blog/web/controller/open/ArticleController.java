package com.ongsat.blog.web.controller.open;

import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.api.ArticleGetNewsParamVO;
import com.ongsat.blog.web.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController(value = "ArticleControllerOpen")
@RequestMapping(value = "/api/article", produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/getNews")
    public Response getNews(@Valid @RequestBody ArticleGetNewsParamVO articleGetNewsParamVO,
                            BindingResult bindingResult) {
        return articleService.getNews(articleGetNewsParamVO);
    }

}
