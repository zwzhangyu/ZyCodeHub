package com.zy.utils.base64;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;

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
        String filePath = "\\已处理\\1675750742768.jpg";
        String base64StrFromFile = getBase64StrFromFile(filePath);
        FileUtil.writeString(base64StrFromFile, new File("repo-tools-component/utils/src/main/java/com/zy/utils/base64/test.txt"), Charset.defaultCharset());
    }
}
