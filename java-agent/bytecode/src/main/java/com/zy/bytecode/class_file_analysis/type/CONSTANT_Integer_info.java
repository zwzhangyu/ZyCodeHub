package com.zy.bytecode.class_file_analysis.type;

import java.nio.ByteBuffer;

public class CONSTANT_Integer_info extends CpInfo {

    private U4 bytes;

    public CONSTANT_Integer_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 连续读取四个字节
        bytes = new U4(codeBuf.get(),codeBuf.get(),codeBuf.get(),codeBuf.get());
    }

}
