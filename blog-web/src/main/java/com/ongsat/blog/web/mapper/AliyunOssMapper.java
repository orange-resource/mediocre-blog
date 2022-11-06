package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.AliyunOssPO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AliyunOssMapper extends BaseMapper<AliyunOssPO>, BaseTableName {

    @Select("select * from" + t_aliyun_oss_space + "where scheme_name = #{schemeName}")
    AliyunOssPO selectBySchemeName(@Param("schemeName") String schemeName);

}
