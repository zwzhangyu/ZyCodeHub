package com.zy.bytecode.class_file_analysis.handler;

import java.nio.ByteBuffer;

public interface ConstantInfoHandler {

    /**
     * 读取
     * @param codeBuf
     */
    void read(ByteBuffer codeBuf) throws Exception;

}
