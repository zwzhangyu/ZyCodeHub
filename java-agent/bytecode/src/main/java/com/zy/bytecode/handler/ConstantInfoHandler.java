package com.zy.bytecode.handler;

import java.nio.ByteBuffer;

public interface ConstantInfoHandler {

    /**
     * 读取
     * @param codeBuf
     */
    void read(ByteBuffer codeBuf) throws Exception;

}
