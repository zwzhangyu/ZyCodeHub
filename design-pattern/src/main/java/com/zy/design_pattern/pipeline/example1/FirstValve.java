package com.zy.design_pattern.pipeline.example1;

public class FirstValve extends ValveBase {

    public void invoke(String s) {
        s = s.replace("11", "first");
        System.out.println("FirstValve阀门处理后结果" + s);
        getNext().invoke(s);
    }
}