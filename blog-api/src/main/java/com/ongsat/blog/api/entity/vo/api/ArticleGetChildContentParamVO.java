package com.ongsat.blog.api.entity.vo.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
public class ArticleGetChildContentParamVO implements Serializable {

    @NotBlank(message = "验证码不能为空")
    private String code;

    @NotBlank(message = "文章标识符不能为空")
    private String uri;

}
