package com.zy.bytecode.class_file_analysis.type;

import java.nio.ByteBuffer;


public class CONSTANT_Class_info extends CpInfo {

    private U2 name_index;

    public CONSTANT_Class_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取两个字节
        this.name_index = new U2(codeBuf.get(), codeBuf.get());
    }

    public U2 getName_index() {
        return name_index;
    }

    public void setName_index(U2 name_index) {
        this.name_index = name_index;
    }
}
