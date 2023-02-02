package com.zy.utils.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 反射工具类
 * @author ZhangYu
 * @date 2021/6/26
 */
public class MyReflectionUtil {


    /**
     * 获取到当前类以及父类的所有属性
     *
     * @param aClass  反射处理类
     * @return 返回当前反射类以及所有父类的属性列表
     */
    public  static  Field[] getAllFields(Class<?> aClass){
        List<Field> fieldList = new ArrayList<>();
        while (aClass!=null){
            //获取当前类所有属性对象
            fieldList.addAll(new ArrayList<>(Arrays.asList(aClass.getDeclaredFields())));
            //获取父类
            aClass=aClass.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return  fieldList.toArray(fields);
    }






}
