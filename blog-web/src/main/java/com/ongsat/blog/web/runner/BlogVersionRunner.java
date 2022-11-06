package com.ongsat.blog.web.runner;

import com.ongsat.blog.api.constant.BlogVersionConstant;
import com.ongsat.blog.api.entity.po.BlogVersionPO;
import com.ongsat.blog.web.mapper.BlogVersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 博客版本检测
 */
@Component
@Order(1)
public class BlogVersionRunner implements ApplicationRunner {

    @Autowired
    private BlogVersionMapper blogVersionMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int count = blogVersionMapper.selectCountByVersionNumber(BlogVersionConstant.CURRENT_VERSION);
        if (count == 0) {
            BlogVersionPO blogVersionPO = new BlogVersionPO();
            blogVersionPO.setVersionNumber(BlogVersionConstant.CURRENT_VERSION);
            blogVersionMapper.insert(blogVersionPO);
        }
    }
}
