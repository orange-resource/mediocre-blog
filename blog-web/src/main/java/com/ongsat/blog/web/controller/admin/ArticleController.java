package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.ArticleDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.ArticleGetByPageParamVO;
import com.ongsat.blog.api.entity.vo.admin.ArticleSaveParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
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
@RestController(value = "ArticleControllerAdmin")
@RequestMapping(value = "/admin/article", produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/getByPage")
    public Response getByPage(@Valid @RequestBody ArticleGetByPageParamVO articleGetByPageParamVO, BindingResult bindingResult) {
        return articleService.getByPage(articleGetByPageParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/save")
    public Response save(@Valid @RequestBody ArticleSaveParamVO articleSaveParamVO, BindingResult bindingResult) {
        return articleService.save(articleSaveParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody ArticleDeleteParamVO articleDeleteParamVO, BindingResult bindingResult) {
        return articleService.delete(articleDeleteParamVO);
    }

}
