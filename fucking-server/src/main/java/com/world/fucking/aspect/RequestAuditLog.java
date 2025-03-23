package com.world.fucking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestAuditLog {
    private static final Logger logger = LoggerFactory.getLogger(RequestAuditLog.class);

    /**
     * 切面
     * execution(* com.world.fucking.controller..*.*(..)) 所有controller 包下的类
     * within(@org.springframework.stereotype.Controller *) 所有 @Controller 类
     * within(@org.springframework.web.bind.annotation.RestController *) 所有 @RestController 类
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void point() {
    }

    /**
     * 处理日志，打印入参
     * @param joinPoint 切点
     */
    @Before("point()")
    public void auditLog(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();
        String simpleName = target.getClass().getSimpleName();
        String methodName = signature.getName();

        logger.info("{}.{}：{}", simpleName, methodName, args[0]);
    }
}
