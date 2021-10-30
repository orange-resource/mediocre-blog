package com.ongsat.blog.web.controller.admin;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.ongsat.blog.api.constant.ApiAuthConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.web.config.annotation.ApiAuth;
import com.ongsat.blog.web.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

/**
 * 对象存储
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/admin/upload", produces = "application/json")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiAuth(type = ApiAuthConstant.ADMIN)
    @PostMapping(value = "/file")
    public Response upload(MultipartFile file) {
        if (null == file || file.isEmpty()) {
            return Response.build(RspCode.FILE_EMPTY);
        }
        try {
            String url = ossService.upload(file);
            Map<String, Object> build = new ResultBuilder().setUrl(url).build();
            return Response.build(RspCode.UPLOAD_SUCCESS, build);
        } catch (Exception e) {
            log.error("文件上传失败: {}", ExceptionUtil.stacktraceToString(e));
            return Response.build(RspCode.UPLOAD_ERROR);
        }
    }

}
