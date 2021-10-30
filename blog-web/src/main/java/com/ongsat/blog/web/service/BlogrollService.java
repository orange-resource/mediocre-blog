package com.ongsat.blog.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.BlogrollPO;
import com.ongsat.blog.api.entity.vo.admin.BlogrollCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.BlogrollDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.BlogrollGetByPageParamVO;
import com.ongsat.blog.api.entity.vo.admin.BlogrollUpdateParamVO;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.BlogrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogrollService extends ServiceImpl<BlogrollMapper, BlogrollPO> {

    @Autowired
    private ConvertObject convertObject;

    public Response getByPage(BlogrollGetByPageParamVO blogrollGetByPageParamVO) {

        Integer limit = blogrollGetByPageParamVO.getLimit();
        Integer offset = blogrollGetByPageParamVO.getOffset();

        Page<Object> page = new Page<>(offset, limit);

        List<BlogrollPO> blogrollPOList = super.baseMapper.selectByPage(page);

        Map<String, Object> build = new ResultBuilder()
                .setList(blogrollPOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    /**
     * todo: 逻辑不严谨
     */
    public Response create(BlogrollCreateParamVO blogrollCreateParamVO) {
        BlogrollPO blogrollPO = convertObject.toBlogrollPO(blogrollCreateParamVO);
        int insert = super.baseMapper.insert(blogrollPO);
        if (insert == 0) return Response.error();
        return Response.success();
    }

    /**
     * todo: 逻辑不严谨
     */
    public Response update(BlogrollUpdateParamVO blogrollUpdateParamVO) {
        BlogrollPO blogrollPO = convertObject.toBlogrollPO(blogrollUpdateParamVO);
        int update = super.baseMapper.updateById(blogrollPO);
        if (update == 0) return Response.error();
        return Response.success();
    }

    /**
     * todo: 逻辑不严谨
     */
    public Response delete(BlogrollDeleteParamVO blogrollDeleteParamVO) {
        String id = blogrollDeleteParamVO.getId();
        int delete = super.baseMapper.deleteById(id);
        if (delete == 0) return Response.error();
        return Response.success();
    }

}
