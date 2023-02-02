package com.zy.delayqueue.redisson.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringUtil
 * Created by LPB on 2021/04/20.
 */
@Component
public class SpringUtil implements ApplicationContextAware {
 
	private static ApplicationContext context;
 
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringUtil.context = context;
	}
 
	public static <T> T getBean(Class<T> clazz) {
		if (clazz == null) {
			return null;
		}
		return context.getBean(clazz);
	}
 
	public static <T> T getBean(String beanId) {
		if (beanId == null) {
			return null;
		}
		return (T) context.getBean(beanId);
	}
 
	public static ApplicationContext getContext() {
		if (context == null) {
			return null;
		}
		return context;
	}
 
}