package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleSearchParamVO;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleUpdateParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleDeleteParamVO;
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
    @PostMapping(value = "/search")
    public Response search(@Valid @RequestBody ArticleSearchParamVO articleSearchParamVO, BindingResult bindingResult) {
        return articleService.searchByAdminPlatform(articleSearchParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/queryCount")
    public Response queryCount() {
        return articleService.queryCount();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/create")
    public Response create(@Valid @RequestBody ArticleCreateParamVO articleCreateParamVO, BindingResult bindingResult) {
        return articleService.create(articleCreateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/update")
    public Response update(@Valid @RequestBody ArticleUpdateParamVO articleUpdateParamVO, BindingResult bindingResult) {
        return articleService.update(articleUpdateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody ArticleDeleteParamVO articleDeleteParamVO, BindingResult bindingResult) {
        return articleService.delete(articleDeleteParamVO);
    }

}
