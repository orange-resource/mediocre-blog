package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.BlogrollPO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogrollMapper extends BaseMapper<BlogrollPO>, BaseTableName {

    @Select("select * from" + t_blogroll_space + "order by create_at desc")
    List<BlogrollPO> selectListByPage(Page page);

    @Select("select * from" + t_blogroll_space + "order by sort desc")
    List<BlogrollPO> selectListBySort();

}
