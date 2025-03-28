package com.world.fucking.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 拦截所有请求路径
 * @author heisenberg
 * @since 1.0.0
 */
@WebFilter(urlPatterns = "/*")
@Slf4j
public class TraceFilter implements Filter {
    private static final String TRACE_HEADER = "X-Trace-ID";

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("TraceFilter初始化完成");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 1. 生成或获取traceId
        String traceId = request.getParameter(TRACE_HEADER); // 优先从请求参数获取
        if (traceId == null) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }

        // 2. 记录请求开始时间
        long startTime = System.currentTimeMillis();
        log.info("{} 请求开始: {}", traceId, startTime);

        // 3. 将traceId添加至响应头
        ((HttpServletResponse) response).addHeader(TRACE_HEADER, traceId);

        // 4. 传递请求
        chain.doFilter(request, response);

        // 5. 计算耗时并记录
        long endTime = System.currentTimeMillis();
        log.info("{} 请求耗时: {} ms%n", traceId, endTime - startTime);
    }

    @Override
    public void destroy() {
        log.info("TraceFilter已销毁");
    }
}
