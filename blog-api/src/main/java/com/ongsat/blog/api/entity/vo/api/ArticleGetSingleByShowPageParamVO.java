package com.ongsat.blog.api.entity.vo.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
public class ArticleGetSingleByShowPageParamVO implements Serializable {

    @NotBlank(message = "标识不能为空")
    private String uri;

}
