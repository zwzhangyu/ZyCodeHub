package com.zy.bytecode.class_file_analysis.type;

import lombok.Data;

@Data
public class FieldInfo {
    private U2 access_flags;
    private U2 name_index;
    private U2 descriptor_index;
    private U2 attributes_count;
    private AttributeInfo[] attributes;
}
