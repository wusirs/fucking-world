package com.world.fucking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 只设置需要跨域访问的ip，设置 "*" 可以允许所有ip访问
                .allowedOrigins("http://127.0.0.1:8081")
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}