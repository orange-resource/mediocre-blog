package com.ongsat.blog.api.entity.vo.api.category;

import com.ongsat.blog.api.entity.po.CategoryPO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryTreeVO extends CategoryPO {

    private List<CategoryPO> children;

}
