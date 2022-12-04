package com.cn.hermimt.purple.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class FileScanner {

    public static final String TYPE_CLASS = ".class";
    public static final String TYPE_YML = ".yml";

    public static List<String> findFileByType(String rootPath, List<String> fileList, String fileType) {
        if (fileList == null) {
            fileList = new ArrayList<>();
        }
        File rootFile = new File(rootPath);
        if (!rootFile.isDirectory()) {
            // 如果当前文件不是目录则添加到文件集合中
            addFile(rootFile.getPath(), fileList, fileType);
        } else {
            // 获取当前目录子目录
            String[] subFileList = rootFile.list();
            assert subFileList != null;
            for (String file : subFileList) {
                // 获取当前处理文件/目录完整路径
                String subFilePath = rootPath + "\\" + file;
                File subFile = new File(subFilePath);
                if (!subFile.isDirectory()) {
                    addFile(subFile.getPath(), fileList, fileType);
                } else {
                    findFileByType(subFilePath, fileList, fileType);
                }
            }
        }
        return fileList;
    }

    /**
     * 将指定后缀名文件添加到满足条件文件集合中
     *
     * @param fileName 当前处理文件名称
     * @param fileList 文件列表容器
     * @param fileType 特定的文件类型
     */
    private static void addFile(String fileName, List<String> fileList, String fileType) {
        if (fileName.endsWith(fileType)) {
            fileList.add(fileName);
        }
    }

    /**
     * 获取真实文件地址路径
     */
    public static String getRealRootPath(String rootPath) {
        if (System.getProperty("os.name").startsWith("Windows")
                && rootPath.startsWith("/")) {
            rootPath = rootPath.substring(1);
            rootPath = rootPath.replaceAll("/", Matcher.quoteReplacement(File.separator));
        }
        return rootPath;
    }

}
