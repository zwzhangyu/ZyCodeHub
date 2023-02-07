package com.zy.utils.base64;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Base64Util相关工具类
 *
 * @author zhangyu
 */
public class Base64Util {

    /**
     * 获取文件的Base64编码
     */
    public static String getBase64StrFromFile(String filePath) {
        byte[] bytes = FileUtil.readBytes(filePath);
        return Base64.encode(bytes);
    }

    public static void main(String[] args) {
        List<File> files = FileUtil.loopFiles("//");
        for (File file : files) {
            String base64StrFromFile = getBase64StrFromFile(file.getAbsolutePath());
            String resultFile = file.getParent() + "\\" + file.getName().replace(".jpg", "") + ".txt";
            FileUtil.writeString(base64StrFromFile, new File(resultFile), Charset.defaultCharset());
        }
    }
}
