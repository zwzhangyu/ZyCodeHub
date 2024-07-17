package com.zy.aop.field_syn;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*******************************************************
 * Created by ZhangYu on 2024/7/9
 * Description :
 * History   :
 *******************************************************/
public class Main {

    public static void main(String[] args) {
        RpcBase2225Bean rpcBase2225Bean = new RpcBase2225Bean();
        rpcBase2225Bean.setName("lisi");
        Map<String, String> fieldMap=new HashMap<>();
        fieldMap.put("姓名","111");
        update(rpcBase2225Bean,new RpcLg1800Bean(),new LgCardVersionIdBean(),fieldMap);
    }

    public static void update(Object fromObj, Object target1, Object target2, Map<String, String> fieldMap) {
        if (fromObj == null || target1 == null || target2 == null || fieldMap == null) {
            return;
        }
        try {
            // 获取 fromObj 的所有字段
            Field[] fromFields = fromObj.getClass().getDeclaredFields();
            for (Field fromField : fromFields) {
                // 使字段可以被访问
                fromField.setAccessible(true);

                // 获取字段的 Description 注解
                Description description = fromField.getAnnotation(Description.class);
                if (description != null) {
                    // 获取字段的值
                    Object value = fromField.get(fromObj);

                    // 如果字段值不为空
                    if (value != null) {
                        // 根据注解 Description 的 value 字段当作 key 去 fieldMap 查找是否可以获取到值
                        String key = description.value();
                        if (fieldMap.containsKey(key)) {
                            // 获取要更新的值
                            String newValue = fieldMap.get(key);

                            // 更新 target1 和 target2 的对应字段
                            updateTargetField(target1, key, newValue);
                            updateTargetField(target2, key, newValue);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(target1);
        System.out.println(target2);
    }

    private static void updateTargetField(Object target, String key, Object newValue) {
        try {
            Field[] targetFields = target.getClass().getDeclaredFields();
            for (Field targetField : targetFields) {
                // 使字段可以被访问
                targetField.setAccessible(true);
                // 获取字段的 Description 注解
                Description description = targetField.getAnnotation(Description.class);
                if (description != null && key.equals(description.value())) {
                    // 更新字段值
                    targetField.set(target, newValue);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
