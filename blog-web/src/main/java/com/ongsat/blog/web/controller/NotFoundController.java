package com.ongsat.blog.web.controller;

import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.RspCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotFoundController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFound() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject(Response.build(RspCode.NOT_FOUND));
        return modelAndView;
    }
}
