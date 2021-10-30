package com.ongsat.blog.api.entity.dataobject;

import com.ongsat.blog.api.entity.po.CategoryPO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryTreeDO extends CategoryPO {

    private List<CategoryPO> children;

}
