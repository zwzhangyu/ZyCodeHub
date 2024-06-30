package com.zy.spring;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) // 必须要是 RUNTIME
@Target(METHOD) // 该注解可以用在方法上
public @interface RequestLog {
    /**
     * SpEL 表达式
     * @return
     */
    String value();
}