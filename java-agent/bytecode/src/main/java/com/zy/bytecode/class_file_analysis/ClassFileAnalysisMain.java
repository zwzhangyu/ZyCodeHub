package com.zy.bytecode.class_file_analysis;

import com.zy.bytecode.class_file_analysis.handler.ClassFileAnalysiser;
import com.zy.bytecode.class_file_analysis.type.ClassFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ClassFileAnalysisMain {

    public static ByteBuffer readFile(String classFilePath) throws Exception {
        File file = new File(classFilePath);
        if (!file.exists()) {
            throw new Exception("file not exists!");
        }
        byte[] byteCodeBuf = new byte[4096];
        int lenght;
        try (InputStream in = new FileInputStream(file)) {
            lenght = in.read(byteCodeBuf);
        }
        if (lenght < 1) {
            throw new Exception("not read byte code.");
        }
        // 将字节数组包装为ByteBuffer
        return ByteBuffer.wrap(byteCodeBuf, 0, lenght).asReadOnlyBuffer();
    }

    public static void main(String[] args) throws Exception {
        // 读取class文件
        ByteBuffer codeBuf = readFile("E:\\ZhangYuCodeHub\\java-agent\\bytecode\\target\\classes\\com\\zy\\bytecode\\class_file_analysis\\test\\HelloWorld.class");
        // 解析class文件
        ClassFile classFile = ClassFileAnalysiser.analysis(codeBuf);
        // 打印魔数解析器解析出来的Magic
        System.out.println(classFile.getMagic().toHexString());
    }

}
