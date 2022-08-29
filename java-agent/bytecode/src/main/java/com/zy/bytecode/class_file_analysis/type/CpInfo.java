package com.zy.bytecode.class_file_analysis.type;

import com.zy.bytecode.class_file_analysis.handler.ConstantInfoHandler;

public abstract class CpInfo implements ConstantInfoHandler {

    /**
     * 标识那种类型的常量
     */
    private U1 tag;

    protected CpInfo(U1 tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "tag=" + tag.toString();
    }

    public static CpInfo newCpInfo(U1 tag) throws Exception {
        // 将占据1个字节的tag转为int整数
        int tagValue = tag.toInt();
        CpInfo info;
        // 判定那种具体的常量类型
        switch (tagValue) {
            case 1:
                info = new CONSTANT_Utf8_info(tag);
                break;
            case 3:
                info = new CONSTANT_Integer_info(tag);
                break;
            case 4:
                info = new CONSTANT_Float_info(tag);
                break;
            case 5:
                info = new CONSTANT_Long_info(tag);
                break;
            case 6:
                info = new CONSTANT_Double_info(tag);
                break;
            case 7:
                info = new CONSTANT_Class_info(tag);
                break;
            case 8:
                info = new CONSTANT_String_info(tag);
                break;
            case 9:
                info = new CONSTANT_Fieldref_info(tag);
                break;
            case 10:
                info = new CONSTANT_Methodref_info(tag);
                break;
            case 11:
                info = new CONSTANT_InterfaceMethodref_info(tag);
                break;
            case 12:
                info = new CONSTANT_NameAndType_info(tag);
                break;
            case 15:
                info = new CONSTANT_MethodHandle_info(tag);
                break;
            case 16:
                info = new CONSTANT_MethodType_info(tag);
                break;
            case 18:
                info = new CONSTANT_InvokeDynamic_info(tag);
                break;
            default:
                throw new Exception("没有找到该TAG=" + tagValue + "对应的常量类型");
        }
        return info;
    }

}
