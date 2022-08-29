package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.ClassAccessFlagUtils;
import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.U2;

import java.nio.ByteBuffer;

public class AccessFlagsHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 3;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setAccess_flags(new U2(codeBuf.get(), codeBuf.get()));
        // 获取访问标志
        U2 accessFlags = classFile.getAccess_flags();
        // 输出为字符串
        System.out.println("------------------------[访问标志]---------------------------------");
        System.out.println(ClassAccessFlagUtils.toClassAccessFlagsString(accessFlags));
    }

}
