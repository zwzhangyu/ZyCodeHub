package com.cn.hermimt.purple.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;


@Component
@Slf4j
public class ScanRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        doScanComponent();
    }

    private void doScanComponent() {
        // 获取target/classes/路径
        String rootPath = Objects.requireNonNull(this.getClass().getResource("/")).getPath();
        log.info("获取类路径地址：{}", rootPath);
        // 获取target/classes目录下面所有的类编译后的class文件
        List<String> fileList = FileScanner.findFileByType(rootPath, null, FileScanner.TYPE_CLASS);
        log.info("待处理文件集合列表：{}", fileList);
        doFilter(rootPath, fileList);
        Map<String, Map<Class, String>> pool = VariablePool.getPool();
        log.info("配置变量池列表：{}", pool);
        // 处理YML配置文件的加载
        EnvInitializer.init();
    }

    /**
     * 遍历class类集合，过滤满足被Component/Controller/Service注解修饰的类对象
     */
    private void doFilter(String rootPath, List<String> fileList) {
        rootPath = FileScanner.getRealRootPath(rootPath);
        for (String fullPath : fileList) {
            String shortName = fullPath.replace(rootPath, "")
                    .replace(FileScanner.TYPE_CLASS, "");
            String packageFileName = shortName.replaceAll(Matcher.quoteReplacement(File.separator), "\\.");
            try {
                Class clazz = Class.forName(packageFileName);
                if (clazz.isAnnotationPresent(Component.class)
                        || clazz.isAnnotationPresent(Controller.class) || clazz.isAnnotationPresent(Service.class)) {
                    log.info("添加Component/Controller/Service注解修饰的类对象{}", clazz);
                    VariablePool.add(clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
