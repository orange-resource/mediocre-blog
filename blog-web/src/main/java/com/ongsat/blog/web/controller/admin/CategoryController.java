package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.CategoryCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.CategoryDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.CategoryUpdateParamVO;
import com.ongsat.blog.api.entity.vo.admin.category.CategoryGetChildParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.CategoryService;
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
@RequestMapping(value = "/admin/category", produces = "application/json")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/getAll")
    public Response getAll() {
        return categoryService.getAll();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/getTopList")
    public Response getTopList() {
        return categoryService.getTopList();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/getChild")
    public Response getChild(@Valid @RequestBody CategoryGetChildParamVO categoryGetChildParamVO, BindingResult bindingResult) {
        return categoryService.getChild(categoryGetChildParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/create")
    public Response create(@Valid @RequestBody CategoryCreateParamVO categoryCreateParamVO, BindingResult bindingResult) {
        return categoryService.create(categoryCreateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/update")
    public Response update(@Valid @RequestBody CategoryUpdateParamVO categoryUpdateParamVO, BindingResult bindingResult) {
        return categoryService.update(categoryUpdateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody CategoryDeleteParamVO categoryDeleteParamVO, BindingResult bindingResult) {
        return categoryService.delete(categoryDeleteParamVO);
    }

}
