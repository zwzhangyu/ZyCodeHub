package com.example.springboot.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorExample implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws
            BeansException {
        System.out.println("BeanPostProcessor 调用"
                + "postProcessBeforeInitialization 方法，参数【"
                + bean.getClass().getSimpleName() + "】【" + beanName + "】 ");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("BeanPostProcessor 调用"
                + "postProcessAfterInitialization 方法，参数【"
                + bean.getClass().getSimpleName() + "】【" + beanName + "】 ");
        return bean;
    }
}