package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.AdminUserPO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserMapper extends BaseMapper<AdminUserPO>, BaseTableName {

    @Select("select count(*) from" + t_admin_user_space + "where username = #{username} and password = #{password}")
    int selectCount(@Param("username") String username, @Param("password") String password);

}
