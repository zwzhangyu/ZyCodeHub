package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.U4;

import java.nio.ByteBuffer;

public class MagicHandler implements BaseByteCodeHandler {
    /**
     * 解释器的排序值
     * 排序排在第一列
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
        // 连续读取四个字节
        classFile.setMagic(new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get()));
        // 魔数的固定值时0xCAFEBABE
        if (!"0xCAFEBABE".equalsIgnoreCase(classFile.getMagic().toHexString())) {
            throw new Exception("这不是一个Class文件");
        }
    }
}
