package com.ongsat.blog.api.entity.vo.admin.carousel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CarouselSaveParamVO implements Serializable {

    private String id;

    private String image;

    private Integer sort;

    private String openUrl;

}
