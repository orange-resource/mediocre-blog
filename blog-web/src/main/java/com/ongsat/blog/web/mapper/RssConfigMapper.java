package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.RssConfigPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RssConfigMapper extends BaseMapper<RssConfigPO>, BaseTableName {

    @Select("select * from" + t_rss_config_space + "where scheme_name = #{schemeName}")
    RssConfigPO selectBySchemeName(@Param("schemeName") String schemeName);

}
