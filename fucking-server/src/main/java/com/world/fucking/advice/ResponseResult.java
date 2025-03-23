package com.world.fucking.advice;

import com.alibaba.fastjson2.JSON;
import com.world.fucking.annotation.ResponseNotIntercept;
import com.world.fucking.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 请求结果处理
 *
 * @author heisenberg
 * @since 1.0.0
 */
@RestControllerAdvice
public class ResponseResult implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否走 beforeBodyWrite 方法
     *
     * @param returnType 返回值类型
     * @param aClass     .
     * @return {@link boolean}
     */
    @Override
    public boolean supports(MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        if (returnType.getDeclaringClass().isAnnotationPresent(ResponseNotIntercept.class)) {
            // 若在类上加了 @ResponseNotIntercept 则该类中的方法不用做统一的拦截
            return false;
        }
        // 若在该方法上加了 @ResponseNotIntercept 则该方法不用做统一的拦截
        return !Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(ResponseNotIntercept.class);
    }

    /**
     * 包装返回值
     *
     * @param body                  返回值
     * @param returnType            返回值类型
     * @param selectedContentType   媒体类型
     * @param selectedConverterType .
     * @param request               req
     * @param response              res
     * @return {@link Object}
     */
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof Result) {
            // 提供一定的灵活度，如果body已经被包装了，就不进行包装
            return body;
        }

        if (body instanceof String) {
            //解决返回值为字符串时，不能正常包装
            return JSON.toJSONString(body);
        }

        return Result.success(body);
    }
}
