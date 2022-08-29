package com.zy.bytecode.class_file_analysis.type;

import java.nio.ByteBuffer;

public class CONSTANT_Long_info extends CpInfo {

    private U4 hight_bytes;
    private U4 low_bytes;

    public CONSTANT_Long_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取高32位
        hight_bytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
        // 读取低32位
        low_bytes = new U4(codeBuf.get(), codeBuf.get(), codeBuf.get(), codeBuf.get());
    }

}
