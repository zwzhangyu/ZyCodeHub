package com.zy.aop.async;


import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.scheduling.annotation.Async;

/*******************************************************
 * Created by ZhangYu on 2024/7/6
 * Description :
 * History   :
 *******************************************************/
public class AsyncExample {

    public static void main(String[] args) {
    }

    private boolean hasAsyncAnnotation(Class<?> targetClass) {


        return AopUtils.canApply(new AnnotationMatchingPointcut(null, Async.class), targetClass);
    }

}
