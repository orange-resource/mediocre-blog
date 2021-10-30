package com.ongsat.blog.web.controller.admin;

import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.entity.vo.admin.carousel.CarouselDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.carousel.CarouselSaveParamVO;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.BannerService;
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
@RequestMapping(value = "/admin/banner", produces = "application/json")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/getAll")
    public Response getAll() {
        return bannerService.getAll();
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/save")
    public Response save(@Valid @RequestBody CarouselSaveParamVO carouselSaveParamVO, BindingResult bindingResult) {
        return bannerService.save(carouselSaveParamVO);
    }

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/delete")
    public Response delete(@Valid @RequestBody CarouselDeleteParamVO carouselDeleteParamVO, BindingResult bindingResult) {
        return bannerService.delete(carouselDeleteParamVO);
    }

}
