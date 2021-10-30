package com.ongsat.blog.web;

import com.ongsat.blog.web.config.MyBanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@ComponentScan("com.ongsat.blog")
@MapperScan("com.ongsat.blog.web.mapper")
public class BlogWebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogWebApplication.class);
        application.setBanner(new MyBanner());
        application.run(args);
    }

}
