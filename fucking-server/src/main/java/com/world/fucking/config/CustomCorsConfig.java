package com.world.fucking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:8081") // 允许的来源域名，* 表示允许所有域名
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
//                        .allowedHeaders("Content-Type", "X-Requested-With") // 允许的请求头
                        .maxAge(3600); // 允许的缓存时间，单位为秒
            }
        };
    }
}
