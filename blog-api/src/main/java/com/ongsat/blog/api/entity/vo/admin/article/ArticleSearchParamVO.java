package com.ongsat.blog.api.entity.vo.admin.article;

import com.ongsat.blog.api.validate.annotation.NumberLimitValidate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class ArticleSearchParamVO implements Serializable {

    private String title;

    private String categoryId;

    private String categoryChildId;

    @NotNull(message = "offset不合法")
    @Min(value = 1, message = "offset不合法")
    private Integer offset;

    @NotNull(message = "limit不合法")
    @NumberLimitValidate(number = {10, 15, 20}, message = "limit不合法")
    private Integer limit;

}
