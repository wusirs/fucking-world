package com.world.fucking.filter;

import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 过滤器实现traceId 获取
 * 注意 logback 日志要引用 org.slf4j.MDC;
 * log4j 要引用 org.apache.log4j.MDC
 *
 * @author heisenberg
 * @since 1.0.0
 */
@Component
public class HeaderFilter implements HandlerInterceptor {
    private static final String TRACE_HEADER = "X-Trace-ID";

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 获取或生成traceId（支持前端传递）
        String traceId = request.getHeader(TRACE_HEADER);
        if (traceId == null || traceId.isEmpty()) {
            traceId = UUID.randomUUID().toString().replace("-", "");
        }

        // 存入MDC供日志追踪[7](@ref)
        MDC.put("traceId", traceId);

        // 将traceId添加至响应头（可选）
        response.addHeader(TRACE_HEADER, traceId);
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) {
        // 清理MDC防止内存泄漏[7](@ref)
        MDC.remove("traceId");
    }
}
