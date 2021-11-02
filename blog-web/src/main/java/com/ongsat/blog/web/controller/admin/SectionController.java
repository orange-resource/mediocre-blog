package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.section.SectionCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionUpdateParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.SectionService;
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
@RequestMapping(value = "/admin/section", produces = "application/json")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/queryAll")
    public Response queryAll() {
        return sectionService.queryAll();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/create")
    public Response create(@Valid @RequestBody SectionCreateParamVO sectionCreateParamVO, BindingResult bindingResult) {
        return sectionService.create(sectionCreateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/update")
    public Response update(@Valid @RequestBody SectionUpdateParamVO sectionUpdateParamVO, BindingResult bindingResult) {
        return sectionService.update(sectionUpdateParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody SectionDeleteParamVO sectionDeleteParamVO, BindingResult bindingResult) {
        return sectionService.delete(sectionDeleteParamVO);
    }

}
