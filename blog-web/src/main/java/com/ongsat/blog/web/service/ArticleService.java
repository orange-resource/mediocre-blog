package com.ongsat.blog.web.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.ArticlePO;
import com.ongsat.blog.api.entity.po.CategoryPO;
import com.ongsat.blog.api.entity.vo.admin.ArticleDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.ArticleGetByPageParamVO;
import com.ongsat.blog.api.entity.vo.admin.ArticleSaveParamVO;
import com.ongsat.blog.api.entity.vo.api.*;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.ArticleMapper;
import com.ongsat.blog.web.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, ArticlePO> {

    @Autowired
    private ConvertObject convertObject;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CategoryMapper categoryMapper;

    public Response getByPage(ArticleGetByPageParamVO articleGetByPageParamVO) {
        String title = articleGetByPageParamVO.getTitle();
        String categoryId = articleGetByPageParamVO.getCategoryId();
        String secondaryCategoryId = articleGetByPageParamVO.getSecondaryCategoryId();
        Integer limit = articleGetByPageParamVO.getLimit();
        Integer offset = articleGetByPageParamVO.getOffset();

        Page<Object> page = new Page<>(offset, limit);
        List<ArticlePO> articlePOList = super.baseMapper.selectByPage(title, categoryId, secondaryCategoryId, page);

        Map<String, Object> build = new ResultBuilder()
                .setList(articlePOList)
                .setTotal(page.getTotal())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response save(ArticleSaveParamVO articleSaveParamVO) {
        ArticlePO articlePO = convertObject.toArticlePO(articleSaveParamVO);

        String categoryId = articlePO.getCategoryId();
        int childCount = categoryMapper.selectChildCount(categoryId);
        if (childCount == 0) {
            articlePO.setCategoryChildId("");
        }

        boolean saveOrUpdate = super.saveOrUpdate(articlePO);
        return saveOrUpdate ? Response.success() : Response.error();
    }

    public Response delete(ArticleDeleteParamVO articleDeleteParamVO) {
        String id = articleDeleteParamVO.getId();
        int delete = super.baseMapper.deleteById(id);
        if (delete == 0) return Response.error();
        return Response.success();
    }

    public Response getSingleByShowPage(ArticleGetSingleByShowPageParamVO articleGetSingleByShowPageParamVO, String codeToken, String ip) {
        ResultBuilder builder = new ResultBuilder();

        String uri = articleGetSingleByShowPageParamVO.getUri();
        ArticlePO articlePO = super.baseMapper.selectByUri(uri);
        if (null == articlePO) {
            builder.setContent(null);
        } else {
            ArticleGetSingleByShowPageVO articleGetSingleByShowPageVO = convertObject.toArticleGetSingleByShowPageVO(articlePO);

            articleGetSingleByShowPageVO.setUri(uri);
            articleGetSingleByShowPageVO.setIsShowChildContent(2);
            builder.setContent(articleGetSingleByShowPageVO);
        }

        return Response.build(RspCode.QUERY_SUCCESS, builder.build());
    }

    public Response getNews(ArticleGetNewsParamVO articleGetNewsParamVO) {
        Integer offset = articleGetNewsParamVO.getOffset();
        Page<Object> page = new Page<>(offset, 5);
        List<ArticlePO> articlePOList = super.baseMapper.selectNews(page);
        List<ArticleCardListVO> articleCardListVOList = convertObject.toArticleCardListVO(articlePOList);

        Map<String, Object> build = new ResultBuilder()
                .setList(articleCardListVOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .setListTotal(articleCardListVOList.size())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public List<ArticleWechatVO> getNewest() {
        Page<Object> page = new Page<>(1, 10);
        List<ArticlePO> articlePOList = super.baseMapper.selectNews(page);
        List<ArticleWechatVO> articleWechatVOS = convertObject.toArticleWechatVO(articlePOList);
        return articleWechatVOS;
    }

    public List<ArticleWechatVO> wechatSearch(String search) {
        Page<Object> page = new Page<>(1, 10);
        List<ArticlePO> articlePOList = super.baseMapper.search(search, page);
        List<ArticleWechatVO> articleWechatVOS = convertObject.toArticleWechatVO(articlePOList);
        return articleWechatVOS;
    }

    public Response getByCategory(ArticleGetByCategoryParamVO articleGetByCategoryParamVO) {
        String categoryUri = articleGetByCategoryParamVO.getCategoryUri();
        Integer offset = articleGetByCategoryParamVO.getOffset();
        Page<Object> page = new Page<>(offset, 5);

        String categoryName = "";
        String categoryId = "";
        CategoryPO categoryPO = categoryMapper.selectById(categoryUri);
        if (null != categoryPO) {
            categoryName = categoryPO.getName();
            if (StrUtil.isBlank(categoryPO.getPid())) {
                categoryId = categoryPO.getId();
            } else {
                CategoryPO categoryPO1 = categoryMapper.selectById(categoryPO.getPid());
                categoryId = categoryPO1.getId();
            }
        }

        List<ArticlePO> articlePOList = super.baseMapper.selectByCategory(categoryUri, page);
        List<ArticleCardListVO> articleCardListVOList = convertObject.toArticleCardListVO(articlePOList);

        Map<String, Object> build = new ResultBuilder()
                .setList(articleCardListVOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .set("categoryId", categoryId)
                .set("categoryName", categoryName)
                .setListTotal(articleCardListVOList.size())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response search(ArticleSearchParamVO articleSearchParamVO) {
        String searchText = articleSearchParamVO.getSearchText();
        Integer offset = articleSearchParamVO.getOffset();

        Page<Object> page = new Page<>(offset, 5);
        List<ArticlePO> articlePOList = super.baseMapper.search(searchText, page);
        List<ArticleCardListVO> articleCardListVOList = convertObject.toArticleCardListVO(articlePOList);

        Map<String, Object> build = new ResultBuilder()
                .setList(articleCardListVOList)
                .setTotal(page.getTotal())
                .set("pages", page.getPages())
                .set("current", page.getCurrent())
                .setListTotal(articleCardListVOList.size())
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

}
