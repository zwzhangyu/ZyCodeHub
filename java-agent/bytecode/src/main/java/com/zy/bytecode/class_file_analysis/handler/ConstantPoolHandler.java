package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.CpInfo;
import com.zy.bytecode.class_file_analysis.type.U1;
import com.zy.bytecode.class_file_analysis.type.U2;

import java.nio.ByteBuffer;

public class ConstantPoolHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 获取常量池计数器的值，占两个字节
        U2 cpLen = new U2(codeBuf.get(), codeBuf.get());
        // 设置class结构常量池计数器值
        classFile.setConstant_pool_count(cpLen);
        // 根据常量池计数器值获取常量池中常量的总数
        int cpInfoLeng = cpLen.toInt() - 1;
        // 先创建对应大小的常量池内存
        classFile.setConstant_pool(new CpInfo[cpInfoLeng]);
        // 解析所有常量
        for (int i = 0; i < cpInfoLeng; i++) {
            // 获取tag
            U1 tag = new U1(codeBuf.get());
            // 获取常量池结构
            CpInfo cpInfo = CpInfo.newCpInfo(tag);
            // 根据具体的常量池结构读取对应字节数据
            cpInfo.read(codeBuf);
            System.out.println("#" + (i + 1) + ":" + cpInfo);
            classFile.getConstant_pool()[i] = cpInfo;
        }
    }

}
