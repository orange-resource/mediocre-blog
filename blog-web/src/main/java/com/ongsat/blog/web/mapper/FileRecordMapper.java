package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.FileRecordPO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRecordMapper extends BaseMapper<FileRecordPO>, BaseTableName {

    @Select("select * from" + t_file_record_space + "order by create_at desc")
    List<FileRecordPO> selectByPage(Page page);

}
