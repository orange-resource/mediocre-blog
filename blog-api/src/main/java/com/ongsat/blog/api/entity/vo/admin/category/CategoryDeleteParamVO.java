package com.ongsat.blog.api.entity.vo.admin.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
public class CategoryDeleteParamVO implements Serializable {

    @NotBlank(message = "分类标识不能为空")
    private String categoryId;

}
