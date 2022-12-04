package com.zy.design_pattern.pipeline.example1;

public class TailValve extends ValveBase {
    public void invoke(String s) {
        s = s.replace("33", "third");
        System.out.println("TailValve阀门处理后结果" + s);
    }
}