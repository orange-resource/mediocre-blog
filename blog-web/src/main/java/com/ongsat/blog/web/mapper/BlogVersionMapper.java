package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ongsat.blog.api.entity.po.BaseTableName;
import com.ongsat.blog.api.entity.po.BlogVersionPO;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogVersionMapper extends BaseMapper<BlogVersionPO>, BaseTableName {
}
