package com.ongsat.blog.api.entity.vo.admin.banner;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BannerSaveParamVO implements Serializable {

    private String id;

    private String masterImageUrl;

    private Integer sort;

    private String openUrl;

}
