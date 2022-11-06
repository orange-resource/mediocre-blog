package com.ongsat.blog.api.entity.vo.admin.blogroll;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BlogrollUpdateParamVO implements Serializable {

    private String id;

    private String name;

    private Integer sort;

    private String openUrl;

}
