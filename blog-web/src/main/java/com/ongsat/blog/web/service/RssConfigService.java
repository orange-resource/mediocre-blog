package com.ongsat.blog.web.service;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.constant.SchemeNameConstant;
import com.ongsat.blog.api.entity.po.ArticlePO;
import com.ongsat.blog.api.entity.po.RssConfigPO;
import com.ongsat.blog.api.entity.vo.admin.rssconfig.RssConfigSaveParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.ArticleMapper;
import com.ongsat.blog.web.mapper.RssConfigMapper;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Slf4j
@Service
public class RssConfigService extends ServiceImpl<RssConfigMapper, RssConfigPO> {

    @Autowired
    private ConvertObject convertObject;

    @Autowired
    private ArticleMapper articleMapper;

    public String getFeedToString() {
        RssConfigPO rssConfigPO = super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
        try {
            SyndFeed feed = new SyndFeedImpl();
            feed.setFeedType("rss_2.0");
            feed.setTitle(rssConfigPO.getTitle());
            feed.setDescription(rssConfigPO.getDescription());
            feed.setLink(rssConfigPO.getLink());
            feed.setAuthor(rssConfigPO.getAuthor());
            // 非必填处理
            if (!StrUtil.isEmpty(rssConfigPO.getLanguage())) {
                feed.setLanguage(rssConfigPO.getLanguage());
            } else {
                feed.setLanguage("zh-cn");
            }
            if (!StrUtil.isEmpty(rssConfigPO.getCopyright())) {
                feed.setCopyright(rssConfigPO.getCopyright());
            }
            if (!StrUtil.isEmpty(rssConfigPO.getWebMaster())) {
                feed.setWebMaster(rssConfigPO.getWebMaster());
            }
            if (!StrUtil.isEmpty(rssConfigPO.getManagingEditor())) {
                feed.setManagingEditor(rssConfigPO.getManagingEditor());
            }

            // 发布时间
            ArticlePO articlePO = articleMapper.selectByLatest();
            if (null == articlePO) {
                feed.setPublishedDate(new Date());
            } else {
                feed.setPublishedDate(articlePO.getCreateAt());
            }

            // 最新文章处理
            List<SyndEntry> articleEntryList = new ArrayList<>();
            Page<Object> page = new Page<>(1, 15);
            List<ArticlePO> articlePOList = articleMapper.selectListByPage(page);
            for (ArticlePO article : articlePOList) {
                SyndEntryImpl entry = new SyndEntryImpl();
                entry.setTitle(article.getTitle());
                SyndContentImpl content = new SyndContentImpl();
                content.setValue(article.getContentHtml());
                entry.setDescription(content);
                if (!StrUtil.isEmpty(rssConfigPO.getAuthor())) {
                    entry.setAuthor(rssConfigPO.getAuthor());
                }
                entry.setPublishedDate(article.getCreateAt());
                entry.setLink(rssConfigPO.getLink() + "/article/" + article.getUri());

                articleEntryList.add(entry);
            }
            feed.setEntries(articleEntryList);

            return new SyndFeedOutput().outputString(feed);
        } catch (Exception e) {
            log.error("get feed error: {}" + ExceptionUtil.stacktraceToString(e));
            return "system error";
        }
    }

    public Response getItemToRsp() {
        RssConfigPO rssConfigPO = super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
        Map<String, Object> build = new ResultBuilder()
                .setDetail(rssConfigPO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public synchronized Response save(RssConfigSaveParamVO rssConfigSaveParamVO) {
        RssConfigPO rssConfigPO = convertObject.toRssConfigPO(rssConfigSaveParamVO);
        rssConfigPO.setSchemeName(SchemeNameConstant.DEFAULT);

        boolean update = super.saveOrUpdate(rssConfigPO);
        if (update) {
            return Response.success();
        }
        return Response.error();
    }

}
