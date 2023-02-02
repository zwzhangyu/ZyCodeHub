package com.zy.design_pattern.callback.example1.demo1;

public class Main {
    public static void main(String[] args) {
        Caller caller = new Caller();
        caller.testCallBack(new CallBackA(), "测试");
    }
}
