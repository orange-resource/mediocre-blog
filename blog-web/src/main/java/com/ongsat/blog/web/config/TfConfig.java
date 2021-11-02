package com.ongsat.blog.web.config;

import com.ongsat.blog.web.config.thydialect.ThkDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TfConfig {

    @Bean
    public ThkDialect thkDialect() {
        return new ThkDialect();
    }

}
