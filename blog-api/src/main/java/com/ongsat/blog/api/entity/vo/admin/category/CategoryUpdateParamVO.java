package com.ongsat.blog.api.entity.vo.admin.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class CategoryUpdateParamVO implements Serializable {

    @NotBlank(message = "分类标识不能为空")
    private String categoryId;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    @NotNull(message = "分类排序不能为空")
    private Integer sort;

}
