package com.bjhy.fast.build.core.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用这个注解的、会在存入日志时自动提取配置的 描述信息加入日志信息中
 * Create by: Jackson
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogDescribe {
    String value() default "";
    int level() default 0;
}
