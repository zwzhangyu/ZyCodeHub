package com.zy.utils.reflection.test;

import com.zy.utils.domain.User;
import com.zy.utils.reflection.MyReflectionUtil;
import org.testng.annotations.Test;

import java.lang.reflect.Field;

public class ReflectionUtilTest {
    /**
     * 测试获取到当前类以及父类的所有属性
     */
    @Test
    public void getAllFieldsTest(){
        Field[] allFields = MyReflectionUtil.getAllFields(User.class);
        for (Field field : allFields) {
            System.out.println(field.getName());
        }
    }

}
