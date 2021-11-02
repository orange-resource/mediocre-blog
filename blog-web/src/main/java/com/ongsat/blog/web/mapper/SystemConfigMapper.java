package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.SystemConfigPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper extends BaseMapper<SystemConfigPO>, BaseTableName {

    @Select("select * from" + t_system_config_space + "where scheme_name = #{schemeName}")
    SystemConfigPO selectBySchemeName(@Param("schemeName") String schemeName);

}
