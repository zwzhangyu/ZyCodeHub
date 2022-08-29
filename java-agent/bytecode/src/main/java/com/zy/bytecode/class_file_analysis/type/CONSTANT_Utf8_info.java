package com.zy.bytecode.class_file_analysis.type;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class CONSTANT_Utf8_info extends CpInfo {

    private U2 length;

    private byte[] bytes;

    public CONSTANT_Utf8_info(U1 tag) {
        super(tag);
    }

    @Override
    public void read(ByteBuffer codeBuf) throws Exception {
        // 读取两个字节，这两个字节存储的是字符串的长度，最大65535
        length = new U2(codeBuf.get(), codeBuf.get());
        // 根据长度创建字节数组
        bytes = new byte[length.toInt()];
        // 读取指定长度的字节到bytes数组，以上获取的是常量信息，这里获取的是常量的具体内容信息
        codeBuf.get(bytes, 0, length.toInt());
    }

    @Override
    public String toString() {
        return super.toString() + ",length=" + length.toInt()
                + ",str=" + new String(bytes, StandardCharsets.UTF_8);
    }

}
