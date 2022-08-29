package com.zy.bytecode.class_file_analysis.handler;

import com.zy.bytecode.class_file_analysis.type.CONSTANT_Class_info;
import com.zy.bytecode.class_file_analysis.type.CONSTANT_Utf8_info;
import com.zy.bytecode.class_file_analysis.type.ClassFile;
import com.zy.bytecode.class_file_analysis.type.U2;

import java.nio.ByteBuffer;

public class InterfacesHandler implements BaseByteCodeHandler {

    @Override
    public int order() {
        return 5;
    }

    @Override
    public void read(ByteBuffer codeBuf, ClassFile classFile) throws Exception {
        // 接口总数interfaces_count
        classFile.setInterfaces_count(new U2(codeBuf.get(), codeBuf.get()));
        int interfaces_count = classFile.getInterfaces_count().toInt();
        // 解析接口表
        U2[] interfaces = new U2[interfaces_count];
        classFile.setInterfaces(interfaces);
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = new U2(codeBuf.get(), codeBuf.get());
        }
        System.out.println("------------------------[接口表]---------------------------------");
        System.out.println("接口总数:" + classFile.getInterfaces_count().toInt());
        if (classFile.getInterfaces_count().toInt() == 0) {
            return;
        }
// 遍历接口表
        for (U2 interfacesIndex : interfaces) {
            // 根据索引从常量池中取得一个CONSTANT_Class_info常量
            CONSTANT_Class_info interfaces_class_info =
                    (CONSTANT_Class_info) classFile.getConstant_pool()
                            [interfacesIndex.toInt() - 1];
            // 根据CONSTANT_Class_info的name_index从常量池取得一个
//  CONSTANT_Utf8_info常量
            CONSTANT_Utf8_info interfaces_class_name_info =
                    (CONSTANT_Utf8_info) classFile.getConstant_pool()
                            [interfaces_class_info.getName_index().toInt() - 1];
            System.out.println(interfaces_class_name_info);
        }
    }

}
