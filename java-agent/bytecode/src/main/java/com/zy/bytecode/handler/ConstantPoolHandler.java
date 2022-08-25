package com.zy.bytecode.handler;

import com.zy.bytecode.type.ClassFile;
import com.zy.bytecode.type.CpInfo;
import com.zy.bytecode.type.U1;
import com.zy.bytecode.type.U2;

import java.nio.ByteBuffer;

public class ConstantPoolHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 2;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 获取常量池技术器的值
        U2 cpLen = new U2(codeBuf.get(), codeBuf.get());
        classFile.setConstant_pool_count(cpLen);
        // 常量池中常量的总数
        int cpInfoLeng = cpLen.toInt() - 1;
        classFile.setConstant_pool(new CpInfo[cpInfoLeng]);
        // 解析所有常量
        for (int i = 0; i < cpInfoLeng; i++) {
            U1 tag = new U1(codeBuf.get());
            CpInfo cpInfo = CpInfo.newCpInfo(tag);
            cpInfo.read(codeBuf);
            System.out.println("#" + (i + 1) + ":" + cpInfo);
            classFile.getConstant_pool()[i] = cpInfo;
        }
    }

}
