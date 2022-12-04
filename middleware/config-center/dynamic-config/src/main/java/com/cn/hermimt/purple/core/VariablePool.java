package com.cn.hermimt.purple.core;

import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 变量池
 *
 * @author zhangyu
 * @date 2022/12/4
 **/
public class VariablePool {

    public static Map<String, Map<Class, String>> pool = new HashMap<>();

    private static final String regex = "^(\\$\\{)(.)+(\\})$";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(regex);
    }

    public static void add(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 匹配所有被@Value注解修饰的属性
            if (field.isAnnotationPresent(Value.class)) {
                Value annotation = field.getAnnotation(Value.class);
                String annoValue = annotation.value();
                // 使用正则检查注解值是否满足${}规则
                if (!pattern.matcher(annoValue).matches()) {
                    continue;
                }
                annoValue = annoValue.replace("${", "");
                annoValue = annoValue.substring(0, annoValue.length() - 1);
                Map<Class, String> clazzMap = Optional.ofNullable(pool.get(annoValue))
                        .orElse(new HashMap<>());
                // 将类和注解属性添加到clazzMap容器中
                clazzMap.put(clazz, field.getName());
                // 将类似@Value("${person.name}")注解上的person.name添加到Map容器上
                pool.put(annoValue, clazzMap);
            }
        }
    }

    public static Map<String, Map<Class, String>> getPool() {
        return pool;
    }
}
