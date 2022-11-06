package com.ongsat.blog.api.entity.dataobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ArticleNewsDO implements Serializable {

    private String title;

    private String uri;

}
