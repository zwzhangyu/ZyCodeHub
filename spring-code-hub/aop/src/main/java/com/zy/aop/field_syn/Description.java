package com.zy.aop.field_syn;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {

    String value() default StringUtils.EMPTY;

    /**
     * 标识需要格式化的日期字段
     */
    String dateFormat() default StringUtils.EMPTY;

    /**
     * 证件类型
     */
    CardTypeEnumDesc cardType();

    /**
     * 是否是图片类型字段
     */
    boolean isImg() default false;

    /**
     * 字段标识键
     */
    String key() default StringUtils.EMPTY;
}
