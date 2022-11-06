package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.BlogVersionPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogVersionMapper extends BaseMapper<BlogVersionPO>, BaseTableName {

    @Select("select count(*) from" + t_blog_version_space + "where version_number = #{versionNumber}")
    int selectCountByVersionNumber(@Param("versionNumber") Integer versionNumber);

}
