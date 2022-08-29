package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.U2;

import java.nio.ByteBuffer;

public class VersionHandler implements BaseByteCodeHandler {
    /**
     * 解释器的排序值
     *
     * @return
     */
    @Override
    public int order() {
        return 0;
    }

    /**
     * 读取
     *
     * @param codeBuf
     * @param classFile
     */
    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 读取4个字节，前两个字节是副版本号，后两个字节是主版本号
        classFile.setMinor_version(new U2(codeBuf.get(), codeBuf.get()));
        classFile.setMajor_version(new U2(codeBuf.get(), codeBuf.get()));
        System.out.println("主版本号：" + classFile.getMajor_version().toInt());
        System.out.println("副版本号：" + classFile.getMinor_version().toInt());


    }
}
