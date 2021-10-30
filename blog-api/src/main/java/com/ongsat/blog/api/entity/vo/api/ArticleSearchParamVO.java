package com.ongsat.blog.api.entity.vo.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class ArticleSearchParamVO implements Serializable {

    @NotBlank(message = "搜索文本不能为空")
    private String searchText;

    @NotNull(message = "offset不能为空")
    @Min(value = 1, message = "offset不合法")
    private Integer offset;

}
