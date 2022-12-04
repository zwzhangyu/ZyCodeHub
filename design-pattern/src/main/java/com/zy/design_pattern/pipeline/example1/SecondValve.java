package com.zy.design_pattern.pipeline.example1;

public class SecondValve extends ValveBase {
    @Override
    public void invoke(String s) {
        s = s.replace("22", "second");
        System.out.println("SecondValve阀门处理后结果" + s);
        getNext().invoke(s);
    }
}