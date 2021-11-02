package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.SectionPO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionMapper extends BaseMapper<SectionPO>, BaseTableName {

    @Select("select * from" + t_section_space + "order by create_at desc")
    List<SectionPO> selectListByCreateAt();

    @Select("select * from" + t_section_space + "order by sort desc")
    List<SectionPO> selectListBySort();

}
