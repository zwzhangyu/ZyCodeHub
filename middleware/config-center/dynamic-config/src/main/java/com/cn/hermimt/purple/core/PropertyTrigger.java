package com.cn.hermimt.purple.core;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 配置检查刷新触发器
 */
@Slf4j
public class PropertyTrigger {

    public static void change(String ymlContent) {
        log.info("最新配置信息：{}", ymlContent);
        // 解析配置中心提交的配置信息
        Map<String, Object> newMap = YamlConverter.convert(ymlContent);
        // 获取系统旧的配置信息
        Map<String, Object> oldMap = EnvInitializer.getEnvMap();
        oldMap.keySet().stream()
                // 检查key是否在最新提交的配置列表中
                .filter(key -> newMap.containsKey(key))
                // 检查新配置和旧配置对应的key值是否相同
                .filter(key -> !newMap.get(key).equals(oldMap.get(key)))
                // 过滤后是key存在但是值不同的数据，则需要进行刷新应用的value值
                .forEach(key -> {
                    log.info("检查到配置中键[{}]对应的值发生变更", key);
                    Object newVal = newMap.get(key);
                    // 将新的值更新旧值对应的位置上
                    oldMap.put(key, newVal);
                    // 触发值变更
                    change(key, newVal);
                });
        EnvInitializer.setEnvMap(oldMap);
    }

    private static void change(String propertyName, Object newValue) {
        log.info("新的值：{}", newValue);
        Map<String, Map<Class, String>> pool = VariablePool.getPool();
        Map<Class, String> classProMap = pool.get(propertyName);
        classProMap.forEach((clazzName, realPropertyName) -> {
            try {
                // 获取IOC容器对应的Bean，并更新对应属性的值
                Object bean = SpringContextUtil.getBean(clazzName);
                Field field = clazzName.getDeclaredField(realPropertyName);
                field.setAccessible(true);
                field.set(bean, newValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
