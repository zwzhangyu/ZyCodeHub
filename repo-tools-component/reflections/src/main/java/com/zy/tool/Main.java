package com.zy.tool;

import cn.hutool.core.lang.Singleton;
import com.zy.tool.bean.Emp;
import com.zy.tool.bean.Person;
import javassist.Modifier;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

/*******************************************************
 * Created by ZhangYu on 2024/7/16
 * Description :
 * History   :
 *******************************************************/
public class Main {


    @Test
    public void test1() {
        Reflections reflections = new Reflections("com.zy.tool");
        Set<Class<?>> subTypes =
                reflections.get(SubTypes.of(Person.class).asClass());
        for (Class<?> subType : subTypes) {
            System.out.println(subType);
        }
    }

    @Test
    public void test2() {
        Reflections reflections = new Reflections("com.zy.tool");
        Set<Class<?>> singletons =
                reflections.get(TypesAnnotated.with(Singleton.class).asClass());
        for (Class<?> subType : singletons) {
            System.out.println(subType);
        }
    }

    @Test
    public void test3() {
        Reflections reflections = new Reflections(".");
        Set<Class<?>> singletons =
                reflections.get(TypesAnnotated.with(Order.class).asClass());
        for (Class<?> subType : singletons) {
            System.out.println(subType);
        }
    }

    // 假如想扫描整个工程的类，直接new一个不带参数的Reflections就好。
    // 值得一提的是，这东西在扫描的时候，连依赖的jar包都不放过。以Spring框架的BeanFactory为例：
    @Test
    public void testSubType2(){
        org.reflections.Reflections reflections = new org.reflections.Reflections();
        Set<Class<? extends BeanFactory>> classes = reflections.getSubTypesOf(BeanFactory.class);

        for(Class clazz : classes) {
            //logger.info(clazz.getName());
            System.out.println("Found: " + clazz.getName());
        }
    }



    // 测试框架自带的工具类
    @Test
    public void testReflectionUtils(){
        // 所有get方法
        Set<Method> getters = ReflectionUtils.getAllMethods(SecurityProperties.User.class,
                org.reflections.ReflectionUtils.withModifier(Modifier.PUBLIC),
                org.reflections.ReflectionUtils.withPrefix("get"),
                org.reflections.ReflectionUtils.withParametersCount(0));

        // 参数是Collection的子类，返回值是boolean
        Set<Method> listMethodsFromCollectionToBoolean =
                org.reflections.ReflectionUtils.getAllMethods(List.class,
                        org.reflections.ReflectionUtils.withParametersAssignableTo(Collection.class),
                        org.reflections.ReflectionUtils.withReturnType(boolean.class));

        // field 带有Column注解，类型是String的子类
        Set<Field> fields = org.reflections.ReflectionUtils.getAllFields(Person.class,
                org.reflections.ReflectionUtils.withAnnotation((Class<? extends Annotation>) Emp.class),
                org.reflections.ReflectionUtils.withTypeAssignableTo(String.class));


        getters.forEach(get->System.out.println("getter:"+get));
        listMethodsFromCollectionToBoolean.forEach(get->System.out.println("listMethodsFromCollectionToBoolean:"+get));
        fields.forEach(get->System.out.println("fields:"+get));
    }

}
