package com.ongsat.blog.api.entity.vo.admin.blogroll;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BlogrollCreateParamVO implements Serializable {

    private String name;

    private Integer sort;

    private String openUrl;

}
