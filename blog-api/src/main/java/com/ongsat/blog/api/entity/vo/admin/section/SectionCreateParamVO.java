package com.ongsat.blog.api.entity.vo.admin.section;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SectionCreateParamVO implements Serializable {

    private String title;

    private String description;

    private Integer sort;

    private String masterImageUrl;

}
