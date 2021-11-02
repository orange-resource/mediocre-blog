package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.systemconfig.SystemConfigSaveParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.SystemConfigService;
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
@RequestMapping(value = "/admin/system", produces = "application/json")
public class SystemConfigController {

    @Autowired
    private SystemConfigService systemConfigService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/get")
    public Response get() {
        return systemConfigService.getItemToRsp();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/save")
    public Response save(@Valid @RequestBody SystemConfigSaveParamVO systemConfigSaveParamVO, BindingResult bindingResult) {
        return systemConfigService.save(systemConfigSaveParamVO);
    }

}
