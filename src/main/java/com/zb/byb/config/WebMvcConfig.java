package com.zb.byb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xieli
 * @date 2019/8/1 17:32
 * @description WebMvcConfig
 */
@Configuration
@EnableConfigurationProperties(EasConfig.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private OpenIdInterceptor openIdInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(openIdInterceptor);
    }
}