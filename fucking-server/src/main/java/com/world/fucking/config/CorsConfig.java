package com.world.fucking.config;

import com.world.fucking.filter.HeaderFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域访问 ip 限制
 * @author heisenberg
 * @since 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private final HeaderFilter headerFilter;

    public CorsConfig(HeaderFilter headerFilter) {
        this.headerFilter = headerFilter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 只设置需要跨域访问的ip，设置 "*" 可以允许所有ip访问
                .allowedOrigins("http://127.0.0.1:8081")
                // "*" 代表所有
                .allowedMethods("GET", "POST", "OPTIONS");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerFilter)
                .addPathPatterns("/**")           // 拦截所有路径
                .excludePathPatterns("/static/**"); // 排除静态资源[1,5](@ref)
    }
}