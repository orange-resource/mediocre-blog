package com.ongsat.blog.web.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleUpdateParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.ArticlePO;
import com.ongsat.blog.api.entity.po.CategoryPO;
import com.ongsat.blog.api.entity.vo.admin.article.ArticleDeleteParamVO;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.ArticleMapper;
import com.ongsat.blog.web.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, ArticlePO> {

    @Autowired
    private ConvertObject convertObject;

    @Autowired
    private CategoryMapper categoryMapper;

    public Response searchByAdminPlatform(com.ongsat.blog.api.entity.vo.admin.article.ArticleSearchParamVO articleSearchParamVO) {
        String title = articleSearchParamVO.getTitle();
        String categoryId = articleSearchParamVO.getCategoryId();
        String categoryChildId = articleSearchParamVO.getCategoryChildId();
        Integer limit = articleSearchParamVO.getLimit();
        Integer offset = articleSearchParamVO.getOffset();

        Page<Object> page = new Page<>(offset, limit);
        List<ArticlePO> articlePOList = super.baseMapper.selectListByAdminPlatformAndPage(title, categoryId, categoryChildId, page);

        Map<String, Object> build = new ResultBuilder()
                .setList(articlePOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response queryCount() {
        int count = super.count();
        Map<String, Object> build = ResultBuilder.create().setCount(count).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(ArticleCreateParamVO articleCreateParamVO) {
        ArticlePO articlePO = convertObject.toArticlePO(articleCreateParamVO);

        String categoryId = articlePO.getCategoryId();
        int childCount = categoryMapper.selectChildCountById(categoryId);
        if (childCount == 0) {
            articlePO.setCategoryChildId("");
        }

        try {
            int insert = super.baseMapper.insert(articlePO);
            if (insert == 0) {
                return Response.error();
            }
            return Response.success();
        } catch (DuplicateKeyException duplicateKeyException) {
            return Response.build(RspCode.URI_REPEAT_WARN);
        }
    }

    public Response update(ArticleUpdateParamVO articleUpdateParamVO) {
        ArticlePO articlePO = convertObject.toArticlePO(articleUpdateParamVO);

        String categoryId = articlePO.getCategoryId();
        int childCount = categoryMapper.selectChildCountById(categoryId);
        if (childCount == 0) {
            articlePO.setCategoryChildId("");
        }

        try {
            int update = super.baseMapper.updateById(articlePO);
            if (update == 0) {
                return Response.error();
            }
            return Response.success();
        } catch (DuplicateKeyException duplicateKeyException) {
            return Response.build(RspCode.URI_REPEAT_WARN);
        }
    }

    public Response delete(ArticleDeleteParamVO articleDeleteParamVO) {
        String id = articleDeleteParamVO.getId();
        int delete = super.baseMapper.deleteById(id);
        if (delete == 0) {
            return Response.error();
        }
        return Response.success();
    }

    public Map<String, Object> queryArticleToMap(String articleUri) {
        ResultBuilder builder = new ResultBuilder();

        ArticlePO articlePO = super.baseMapper.selectByUri(articleUri);
        builder.setContent(articlePO);

        return builder.build();
    }

    public Map<String, Object> queryArticleByPageToMap(int offset) {
        Page<Object> page = new Page<>(offset, 5);
        List<ArticlePO> articlePOList = super.baseMapper.selectListByPage(page);

        Map<String, Object> build = new ResultBuilder()
                .setList(articlePOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .build();
        return build;
    }

    public Map<String, Object> queryArticleByCategoryToMap(String categoryId, int offset) {
        Page<Object> page = new Page<>(offset, 5);

        String categoryName = "";
        String categoryIdCopy = "";
        CategoryPO categoryPO = categoryMapper.selectById(categoryId);
        if (null != categoryPO) {
            categoryName = categoryPO.getName();
            if (StrUtil.isBlank(categoryPO.getPid())) {
                categoryIdCopy = categoryPO.getId();
            } else {
                CategoryPO categoryParentPO = categoryMapper.selectById(categoryPO.getPid());
                categoryIdCopy = categoryParentPO.getId();
            }
        }

        List<ArticlePO> articlePOList = super.baseMapper.selectByCategoryId(categoryIdCopy, page);

        Map<String, Object> build = new ResultBuilder()
                .setList(articlePOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .set("categoryId", categoryId)
                .set("categoryName", categoryName)
                .build();
        return build;
    }

    public Map<String, Object> searchByOpenApiPlatformToMap(String keyword, int offset) {
        Page<Object> page = new Page<>(offset, 5);
        List<ArticlePO> articlePOList = super.baseMapper.selectListByOpenApiPlatformAndPage(keyword, page);

        Map<String, Object> build = new ResultBuilder()
                .setList(articlePOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .build();
        return build;
    }

}
