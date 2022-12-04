package com.cn.hermimt.purple.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class EnvInitializer {

    private static Map<String, Object> envMap = new HashMap<>();

    public static void init() {
        log.info("初始化YML配置文件");
        String rootPath = EnvInitializer.class.getResource("/").getPath();
        // 获取项目下所有的yml配置文件
        List<String> fileList = FileScanner.findFileByType(rootPath, null, FileScanner.TYPE_YML);
        for (String ymlFilePath : fileList) {
            rootPath = FileScanner.getRealRootPath(rootPath);
            ymlFilePath = ymlFilePath.replace(rootPath, "");
            YamlMapFactoryBean yamlMapFb = new YamlMapFactoryBean();
            yamlMapFb.setResources(new ClassPathResource(ymlFilePath));
            Map<String, Object> map = yamlMapFb.getObject();
            // 转换配置文件对象
            YamlConverter.doConvert(map, null, envMap);
        }
    }

    public static void setEnvMap(Map<String, Object> envMap) {
        EnvInitializer.envMap = envMap;
    }

    public static Map<String, Object> getEnvMap() {
        return envMap;
    }
}
