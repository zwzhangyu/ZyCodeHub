package com.zy.design_pattern.callback.example1.demo2;

public class Caller {
    private Operation callbackReference;

    public Caller(Operation op) {
        this.callbackReference = op;
    }

    public void call() {
        callbackReference.operate();
    }
}
