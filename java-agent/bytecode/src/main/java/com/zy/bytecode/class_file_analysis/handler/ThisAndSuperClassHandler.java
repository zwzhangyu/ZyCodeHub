package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.type.CONSTANT_Class_info;
import com.zy.bytecode.class_file_analysis.type.CONSTANT_Utf8_info;
import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.U2;

import java.nio.ByteBuffer;

public class ThisAndSuperClassHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        classFile.setThis_class(new U2(codeBuf.get(), codeBuf.get()));
        classFile.setSuper_class(new U2(codeBuf.get(), codeBuf.get()));
        // this_class
        U2 this_class = classFile.getThis_class();
// 根据this_class到常量池获取CONSTANT_Class_info常量
// 由于常量池的索引是从1开始的，所以需要将索引减1取得数组下标
        CONSTANT_Class_info this_class_cpInfo =
                (CONSTANT_Class_info) classFile.getConstant_pool()[this_class.toInt() - 1];
        System.out.println("------------------------[ThisAndSuperClass]---------------------------------");
        CONSTANT_Utf8_info this_class_name = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()
                        [this_class_cpInfo.getName_index().toInt() - 1];
        System.out.println(this_class_name);
        // super_class
        U2 super_class = classFile.getSuper_class();
        CONSTANT_Class_info super_class_cpInfo = (CONSTANT_Class_info)
                classFile.getConstant_pool()[super_class.toInt() - 1];

        CONSTANT_Utf8_info supor_class_name = (CONSTANT_Utf8_info)
                classFile.getConstant_pool()
                        [super_class_cpInfo.getName_index().toInt() - 1];
        System.out.println(supor_class_name);
    }

}
