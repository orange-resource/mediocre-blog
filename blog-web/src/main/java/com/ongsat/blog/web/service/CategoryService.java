package com.ongsat.blog.web.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.CategoryPO;
import com.ongsat.blog.api.entity.vo.admin.CategoryCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.CategoryDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.CategoryUpdateParamVO;
import com.ongsat.blog.api.entity.vo.admin.category.CategoryGetChildParamVO;
import com.ongsat.blog.web.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryService extends ServiceImpl<CategoryMapper, CategoryPO> {

    public Response getAll() {
        List<CategoryPO> list = super.baseMapper.selectAll();

        Map<String, Object> build = new ResultBuilder()
                .setList(list)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getTopList() {
        List<CategoryPO> categoryPOList = super.baseMapper.selectTopList();

        Map<String, Object> build = new ResultBuilder()
                .setList(categoryPOList)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response getChild(CategoryGetChildParamVO categoryGetChildParamVO) {
        String id = categoryGetChildParamVO.getId();
        List<CategoryPO> categoryPOList = super.baseMapper.getChild(id);
        Map<String, Object> build = new ResultBuilder()
                .setList(categoryPOList)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    /**
     * TODO: 逻辑不严谨
     */
    public Response create(CategoryCreateParamVO categoryCreateParamVO) {
        String categoryId = categoryCreateParamVO.getCategoryId();
        String name = categoryCreateParamVO.getName();
        Integer sort = categoryCreateParamVO.getSort();

        CategoryPO categoryPO = new CategoryPO();
        categoryPO.setName(name);
        categoryPO.setSort(sort);
        if (!StrUtil.isBlank(categoryId)) {
            categoryPO.setPid(categoryId);
        }
        int insert = super.baseMapper.insert(categoryPO);
        if (insert == 0) return Response.error();
        return Response.success();
    }

    /**
     * 逻辑不严谨
     */
    public Response update(CategoryUpdateParamVO categoryUpdateParamVO) {
        String categoryId = categoryUpdateParamVO.getCategoryId();
        String name = categoryUpdateParamVO.getName();
        Integer sort = categoryUpdateParamVO.getSort();

        CategoryPO categoryPO = new CategoryPO();
        categoryPO.setId(categoryId);
        categoryPO.setSort(sort);
        categoryPO.setName(name);

        int update = super.baseMapper.updateById(categoryPO);
        if (update == 0) return Response.error();
        return Response.success();
    }

    /**
     * 逻辑不严谨
     */
    public Response delete(CategoryDeleteParamVO categoryDeleteParamVO) {
        String categoryId = categoryDeleteParamVO.getCategoryId();
        int delete = super.baseMapper.deleteById(categoryId);
        if (delete == 0) return Response.error();
        return Response.success();
    }

}
