package com.world.fucking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLockAnnotation {
    String value() default "";

    /**
     * 期望时间 单位毫秒
     * @return int
     */
    int expireTime() default 0;
}
