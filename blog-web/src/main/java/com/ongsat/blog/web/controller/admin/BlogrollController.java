package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollSearchParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.blogroll.BlogrollUpdateParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.BlogrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/admin/blogroll", produces = "application/json")
public class BlogrollController {

    @Autowired
    private BlogrollService blogrollService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/search")
    public Response search(@Valid @RequestBody BlogrollSearchParamVO blogrollSearchParamVO, BindingResult bindingResult) {
        return blogrollService.search(blogrollSearchParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/create")
    public Response create(@Valid @RequestBody BlogrollCreateParamVO blogrollCreateParamVO, BindingResult bindingResult) {
        return blogrollService.create(blogrollCreateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/update")
    public Response create(@Valid @RequestBody BlogrollUpdateParamVO blogrollUpdateParamVO, BindingResult bindingResult) {
        return blogrollService.update(blogrollUpdateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody BlogrollDeleteParamVO blogrollDeleteParamVO, BindingResult bindingResult) {
        return blogrollService.delete(blogrollDeleteParamVO);
    }

}
