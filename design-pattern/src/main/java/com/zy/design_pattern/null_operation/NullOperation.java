package com.zy.design_pattern.null_operation;

public class NullOperation extends AbstractOperation {
    @Override
    void request() {
        System.out.println("do nothing");
    }
}
