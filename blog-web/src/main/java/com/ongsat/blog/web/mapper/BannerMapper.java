package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BannerPO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerMapper extends BaseMapper<BannerPO>, BaseTableName {

    @Select("select * from" + t_banner_space + "order by create_at desc")
    List<BannerPO> selectListByCreateAt();

    @Select("select * from" + t_banner_space + "order by sort desc")
    List<BannerPO> selectListBySort();

}
