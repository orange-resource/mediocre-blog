package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.dataobject.CategoryTreeDO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.CategoryPO;
import com.ongsat.blog.api.entity.vo.api.CategoryTreeVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<CategoryPO>, BaseTableName {

    @Select("select * from"+ t_category_space +"where pid is null order by sort desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "children", column = "id",
                    many = @Many(select = "com.ongsat.blog.blog_web.mapper.CategoryMapper.selectListById")
            )
    })
    List<CategoryTreeDO> selectCategoryTree();

    @Select("select * from"+ t_category_space +"where pid is null order by sort desc")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "children", column = "id",
                    many = @Many(select = "com.ongsat.blog.blog_web.mapper.CategoryMapper.selectListById")
            )
    })
    List<CategoryTreeVO> selectCategoryTreeVO();

    @Select("select * from"+ t_category_space +"where pid = #{id}")
    List<CategoryPO> selectListById(@Param("id") String categoryId);

    @Select("select * from" + t_category_space + "order by sort desc")
    List<CategoryPO> selectAll();

    @Select("select * from" + t_category_space + "where pid is null or pid = ''")
    List<CategoryPO> selectTopList();

    @Select("select * from" + t_category_space + "where pid = #{id} order by sort desc")
    List<CategoryPO> getChild(@Param("id") String id);

    @Select("select count(*) from" + t_category_space + "where pid = #{id}")
    int selectChildCount(@Param("id") String id);

}
