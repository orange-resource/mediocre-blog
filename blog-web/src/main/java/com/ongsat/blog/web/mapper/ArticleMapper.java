package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ongsat.blog.api.entity.po.ArticlePO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<ArticlePO>, BaseTableName {

    @Select("select * from" + t_article_space + "order by create_at desc limit 1")
    ArticlePO selectByLatest();

    @Select("<script>" +
            "select * from" + t_article_space +
            "<where>" +
            "<if test=\"title != null and title != ''\"> and title like concat('%', #{title}, '%') </if>" +
            "<if test=\"categoryId != null and categoryId != ''\"> or category_id = #{categoryId} </if>" +
            "<if test=\"categoryChildId != null and categoryChildId != ''\"> or category_child_id = #{categoryChildId} </if>" +
            "</where>" +
            "order by create_at desc " +
            "</script>")
    List<ArticlePO> selectListByAdminPlatformAndPage(
            @Param("title") String title,
            @Param("categoryId") String categoryId,
            @Param("categoryChildId") String categoryChildId,
            Page page
    );

    @Select("select * from" + t_article_space + "where is_show = 1 order by create_at desc limit 0,5")
    List<ArticlePO> selectListByLatestAndLimit();

    @Select("select * from" + t_article_space + "where is_show = 1 and is_recommend = 1 order by create_at desc")
    List<ArticlePO> selectListByRecommend();

    @Select("select * from" + t_article_space + "where is_show = 1 and uri = #{uri}")
    ArticlePO selectByUri(@Param("uri") String uri);

    @Select("select * from" + t_article_space + "where is_show = 1 order by create_at desc")
    List<ArticlePO> selectListByPage(Page page);

    @Select("select * from" + t_article_space +
            "where is_show = 1 and (category_id = #{categoryId} or category_child_id = #{categoryId}) order by create_at desc")
    List<ArticlePO> selectByCategoryId(@Param("categoryId") String categoryId, Page page);

    @Select("select * from" + t_article_space +
            "where is_show = 1 and " +
            "(title like concat('%', #{keyword}, '%') or description like concat('%', #{keyword}, '%')) " +
            "order by create_at desc")
    List<ArticlePO> selectListByOpenApiPlatformAndPage(@Param("keyword") String keyword, Page page);

}
