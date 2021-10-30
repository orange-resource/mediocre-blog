package com.ongsat.blog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ongsat.blog.api.entity.po.ArticlePO;
import com.ongsat.blog.api.entity.po.BaseTableName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<ArticlePO>, BaseTableName {

    @Select("<script>" +
            "select * from" + t_article_space +
            "<where>" +
            "<if test=\"title != null and title != ''\"> and title like concat('%', #{title}, '%') </if>" +
            "<if test=\"categoryId != null and categoryId != ''\"> or category_id = #{categoryId} </if>" +
            "<if test=\"secondaryCategoryId != null and secondaryCategoryId != ''\"> or secondary_category_id = #{secondaryCategoryId} </if>" +
            "</where>" +
            "order by create_at desc " +
            "</script>")
    List<ArticlePO> selectByPage(
            @Param("title") String title,
            @Param("categoryId") String categoryId,
            @Param("secondaryCategoryId") String secondaryCategoryId,
            Page page
    );

    @Select("select * from" + t_article_space + "where is_show = 1 order by create_at desc limit 0,5")
    List<ArticlePO> selectByNews();

    @Select("select * from" + t_article_space + "where is_show = 1 and is_recommend = 1 order by create_at desc")
    List<ArticlePO> selectByRecommend();

    @Select("select * from" + t_article_space + "where is_show = 1 and uri = #{uri}")
    ArticlePO selectByUri(@Param("uri") String uri);

    @Select("select * from" + t_article_space + "where is_show = 1 order by create_at desc")
    List<ArticlePO> selectNews(Page page);

    @Select("select * from" + t_article_space +
            "where is_show = 1 and (category_id = #{categoryUri} or secondary_category_id = #{categoryUri}) order by create_at desc")
    List<ArticlePO> selectByCategory(@Param("categoryUri") String categoryUri, Page page);

    @Select("select * from" + t_article_space +
            "where is_show = 1 and " +
            "(title like concat('%', #{searchText}, '%') or description like concat('%', #{searchText}, '%')) " +
            "order by create_at desc")
    List<ArticlePO> search(@Param("searchText") String searchText, Page page);

}
