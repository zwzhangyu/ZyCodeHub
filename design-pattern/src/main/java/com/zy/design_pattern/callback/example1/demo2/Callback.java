package com.zy.design_pattern.callback.example1.demo2;

public class Callback {
    public static void main(String[] args) {
        Caller mycaller = new Caller(new MyImplement());
        mycaller.call();
    }
}
