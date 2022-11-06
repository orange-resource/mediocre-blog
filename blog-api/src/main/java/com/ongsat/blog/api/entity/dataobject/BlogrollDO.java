package com.ongsat.blog.api.entity.dataobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BlogrollDO implements Serializable {

    private String name;

    private Integer sort;

    private String openUrl;

}
