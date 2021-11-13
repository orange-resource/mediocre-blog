package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.entity.vo.admin.rssconfig.RssConfigSaveParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.RssConfigService;
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
@RequestMapping(value = "/admin/rss/config", produces = "application/json")
public class RssConfigController {

    @Autowired
    private RssConfigService rssConfigService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/get")
    public Response get() {
        return rssConfigService.getItemToRsp();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/save")
    public Response save(@Valid @RequestBody RssConfigSaveParamVO rssConfigSaveParamVO, BindingResult bindingResult) {
        return rssConfigService.save(rssConfigSaveParamVO);
    }

}
