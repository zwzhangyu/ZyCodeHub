package com.zy.bytecode.class_file_analysis.type;

import lombok.Data;

@Data
public class Code_attribute {

    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 max_stack;
    private U2 max_locals;
    private U4 code_length;
    private byte[] code;
    private U4 exception_table_length;
    private Exception[] exception_table;
    private U2 attributes_count;
    private AttributeInfo[] attributes;
    // 异常表中每项的结构
    public static class Exception {
        private U2 start_pc;
        private U2 end_pc;
        private U2 handler_pc;
        private U2 catch_type;
    }

}
