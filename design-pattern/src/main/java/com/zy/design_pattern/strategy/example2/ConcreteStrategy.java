package com.zy.design_pattern.strategy.example2;

public class ConcreteStrategy extends Strategy {
    /**
     * 算法方法
     */
    @Override
    public void algorithmInterface() {
        System.out.println("默认算法");
    }
}
