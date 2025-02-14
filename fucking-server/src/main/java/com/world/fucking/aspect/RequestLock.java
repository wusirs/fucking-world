package com.world.fucking.aspect;

import com.world.fucking.annotation.RequestLockAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLock.class);

    /**
     * @param pjp 方法连接点
     * @param requestLockAnnotation 方法 RequestLockAnnotation 注解
     * @return Object
     */
    @Around(value = "@annotation(requestLockAnnotation)", argNames = "pjp,requestLockAnnotation")
    public Object requestLock(ProceedingJoinPoint pjp, RequestLockAnnotation requestLockAnnotation) {
        Object resObj = null;
        try {
            resObj = pjp.proceed();
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
        }
        return resObj;
    }
}
