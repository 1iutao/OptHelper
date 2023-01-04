package com.common.opthelperserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author : liutao
 * @Date : 2023/1/4
 * @Description 跨域处理
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //都运行跨域
        registry.addMapping("/**")
                //跨域来源
                .allowedOrigins("Http://localhost:8080", "null")
                //运行方法
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                //是否运行携带信息
                .allowCredentials(true)
                //最大响应时间
                .maxAge(3600);
    }
}
