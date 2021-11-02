package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.dataobject.CategoryTreeDO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.CategoryPO;
import com.ongsat.blog.api.entity.vo.api.category.CategoryTreeVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<CategoryPO>, BaseTableName {

    @Select("select * from" + t_category_space + "where pid is null or pid = '' order by sort desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "children", column = "id",
                    many = @Many(select = "com.ongsat.blog.web.mapper.CategoryMapper.selectChildListById")
            )
    })
    List<CategoryTreeVO> selectCategoryTree();

    @Select("select * from" + t_category_space + "order by sort desc")
    List<CategoryPO> selectListBySort();

    @Select("select * from" + t_category_space + "where pid is null or pid = ''")
    List<CategoryPO> selectOneLevelList();

    @Select("select * from" + t_category_space + "where pid = #{id} order by sort desc")
    List<CategoryPO> selectChildListById(@Param("id") String id);

    @Select("select count(*) from" + t_category_space + "where pid = #{id}")
    int selectChildCountById(@Param("id") String id);

}
