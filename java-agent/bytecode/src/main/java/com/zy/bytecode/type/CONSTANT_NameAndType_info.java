package com.zy.bytecode.type;

import java.nio.ByteBuffer;

public class CONSTANT_NameAndType_info extends CpInfo {

    private U2 name_index;
    private U2 descriptor_index;

    public CONSTANT_NameAndType_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 名称索引
        name_index = new U2(codeBuf.get(), codeBuf.get());
        // 描述符索引
        descriptor_index = new U2(codeBuf.get(), codeBuf.get());
    }

}
